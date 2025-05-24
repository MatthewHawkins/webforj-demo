// package org.example.service;

// import com.google.gson.Gson;
// import com.google.gson.JsonArray;
// import com.google.gson.JsonElement;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;
// import com.google.gson.reflect.TypeToken;
// import org.example.model.StockData;
// import org.java_websocket.client.WebSocketClient;
// import org.java_websocket.handshake.ServerHandshake;

// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URI;
// import java.net.URL;
// import java.text.DecimalFormat;
// import java.util.*;
// import java.util.concurrent.*;

// public class StockDataService {

//     private static final String API_KEY = "d0obt2pr01qsib2blql0d0obt2pr01qsib2blqlg";
//     private static final String API_URL = "https://finnhub.io/api/v1/quote?symbol=%s&token=%s";
//     private static final String LOGO_DEV_BASE = "https://logo.dev/api/v1/";

//     private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#0.00");
//     private final Gson gson = new Gson();

//     private final Map<String, StockData> stockMap = new ConcurrentHashMap<>();
//     private final Map<String, Double> latestPrices = new ConcurrentHashMap<>();
//     private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//     public List<StockData> initializeFromJson(String jsonString) {
//         List<CompanyMeta> companies = gson.fromJson(jsonString, new TypeToken<List<CompanyMeta>>() {}.getType());

//         for (CompanyMeta meta : companies) {
//             StockData stock = fetchStockData(meta.getTicker(), meta.getName(), meta.getDomain());
//             if (stock != null) {
//                 stockMap.put(meta.getTicker(), stock);
//             }
//             // try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
//         }

//         startWebSocketUpdates();
//         return new ArrayList<>(stockMap.values());
//     }

//     public StockData fetchStockData(String ticker, String companyName, String domain) {
//         try {
//             String urlStr = String.format(API_URL, ticker, API_KEY);
//             URL url = new URL(urlStr);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("GET");

//             JsonObject node = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();

//             double current = node.has("c") && !node.get("c").isJsonNull() ? node.get("c").getAsDouble() : 0.0;
//             double previous = node.has("pc") && !node.get("pc").isJsonNull() ? node.get("pc").getAsDouble() : 0.0;
//             long volume = node.has("v") && !node.get("v").isJsonNull() ? node.get("v").getAsLong() : 0;

//             if (current == 0.0 && previous == 0.0) {
//                 System.out.println("Warning: No quote data available for " + ticker);
//                 return null;
//             }

//             double change = current - previous;
//             double percent = previous != 0.0 ? (change / previous) * 100 : 0.0;
//             boolean isUp = change >= 0;

//             String arrow = isUp ? "🔺" : "🔻";
//             String formattedChange = arrow + " " + PERCENT_FORMAT.format(percent) + "%";
//             String formattedVolume = formatVolume(volume);

//             String logoUrl = LOGO_DEV_BASE + domain;

//             StockData stock = new StockData();
//             stock.setTicker(ticker);
//             stock.setCompanyName(companyName);
//             stock.setCompanyDomain(domain);
//             stock.setLogoUrl(logoUrl);
//             stock.setCurrentPrice(current);
//             stock.setPreviousClose(previous);
//             stock.setPriceChange(change);
//             stock.setPriceChangePercent(percent);
//             stock.setPriceUp(isUp);
//             stock.setFormattedChange(formattedChange);
//             stock.setVolume(volume);
//             stock.setFormattedVolume(formattedVolume);
//             stock.setLastUpdated(System.currentTimeMillis());

//             return stock;
//         } catch (Exception e) {
//             System.out.println("Error fetching data for " + ticker + ": " + e.getMessage());
//             return null;
//         }
//     }

//     private String formatVolume(long volume) {
//         if (volume >= 1_000_000) {
//             return (volume / 1_000_000) + "M";
//         } else if (volume >= 1_000) {
//             return (volume / 1_000) + "K";
//         }
//         return String.valueOf(volume);
//     }

//     private void startWebSocketUpdates() {
//         try {
//             WebSocketClient client = new WebSocketClient(new URI("wss://ws.finnhub.io?token=" + API_KEY)) {
//                 @Override
//                 public void onOpen(ServerHandshake handshake) {
//                     System.out.println("WebSocket opened");
//                     stockMap.keySet().forEach(symbol -> send("{\"type\":\"subscribe\",\"symbol\":\"" + symbol + "\"}"));
//                 }

//                 @Override
//                 public void onMessage(String message) {
//                     try {
//                         JsonObject json = JsonParser.parseString(message).getAsJsonObject();
//                         if (!"trade".equals(json.get("type").getAsString())) return;

//                         JsonArray dataArray = json.getAsJsonArray("data");
//                         for (JsonElement el : dataArray) {
//                             JsonObject tick = el.getAsJsonObject();
//                             String symbol = tick.get("s").getAsString();
//                             double price = tick.get("p").getAsDouble();
//                             latestPrices.put(symbol, price);
//                         }
//                     } catch (Exception e) {
//                         System.out.println("Error parsing WebSocket message: " + e.getMessage());
//                     }
//                 }

//                 @Override
//                 public void onClose(int code, String reason, boolean remote) {
//                     System.out.println("WebSocket closed: " + reason);
//                     scheduler.shutdown();
//                 }

//                 @Override
//                 public void onError(Exception ex) {
//                     System.out.println("WebSocket error: " + ex.getMessage());
//                 }
//             };

//             client.connect();

//             scheduler.scheduleAtFixedRate(() -> {
//                 for (Map.Entry<String, Double> entry : latestPrices.entrySet()) {
//                     String symbol = entry.getKey();
//                     double price = entry.getValue();
//                     StockData stock = stockMap.get(symbol);
//                     if (stock != null) {
//                         stock.setCurrentPrice(price);
//                         stock.setLastUpdated(System.currentTimeMillis());
//                         System.out.println("Updated " + symbol + " → $" + price);
//                     }
//                 }
//             }, 0, 3, TimeUnit.SECONDS);

//         } catch (Exception e) {
//             System.out.println("Failed to start WebSocket: " + e.getMessage());
//         }
//     }

//     public static class CompanyMeta {
//         private String ticker;
//         private String name;
//         private String domain;

//         public CompanyMeta(String ticker, String name, String domain) {
//             this.ticker = ticker;
//             this.name = name;
//             this.domain = domain;
//         }

//         public String getTicker() { return ticker; }
//         public String getName() { return name; }
//         public String getDomain() { return domain; }
//     }
// }


package org.example.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.webforj.App;

import org.example.model.StockData;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;

public final class StockDataService {

    private static final String API_KEY = "d0obt2pr01qsib2blql0d0obt2pr01qsib2blqlg";
    private static final String API_URL = "https://finnhub.io/api/v1/quote?symbol=%s&token=%s";
    private static final String LOGO_DEV_BASE = "https://logo.dev/api/v1/";

    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#0.00");
    private static final Gson gson = new Gson();

    private static final Map<String, StockData> stockMap = new ConcurrentHashMap<>();
    private static final Map<String, Double> latestPrices = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private StockDataService() {}

    public static List<StockData> initializeFromJson(String jsonString) {
        List<CompanyMeta> companies = gson.fromJson(jsonString, new TypeToken<List<CompanyMeta>>() {}.getType());

        for (CompanyMeta meta : companies) {
            StockData stock = fetchStockData(meta.getTicker(), meta.getName(), meta.getDomain());
            if (stock != null) {
                stockMap.put(meta.getTicker(), stock);
            }
        }

        startWebSocketUpdates();
        return new ArrayList<>(stockMap.values());
    }

    public static StockData fetchStockData(String ticker, String companyName, String domain) {
        try {
            String urlStr = String.format(API_URL, ticker, API_KEY);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JsonObject node = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();

            double current = node.has("c") && !node.get("c").isJsonNull() ? node.get("c").getAsDouble() : 0.0;
            double previous = node.has("pc") && !node.get("pc").isJsonNull() ? node.get("pc").getAsDouble() : 0.0;
            long volume = node.has("v") && !node.get("v").isJsonNull() ? node.get("v").getAsLong() : 0;

            if (current == 0.0 && previous == 0.0) {
                System.out.println("Warning: No quote data available for " + ticker);
                return null;
            }

            double change = current - previous;
            double percent = previous != 0.0 ? (change / previous) * 100 : 0.0;
            boolean isUp = change >= 0;

            String arrow = isUp ? "🔺" : "🔻";
            String formattedChange = arrow + " " + PERCENT_FORMAT.format(percent) + "%";
            String formattedVolume = formatVolume(volume);

            String logoUrl = LOGO_DEV_BASE + domain;

            StockData stock = new StockData();
            stock.setTicker(ticker);
            stock.setCompanyName(companyName);
            stock.setCompanyDomain(domain);
            stock.setLogoUrl(logoUrl);
            stock.setCurrentPrice(current);
            stock.setPreviousClose(previous);
            stock.setPriceChange(change);
            stock.setPriceChangePercent(percent);
            stock.setPriceUp(isUp);
            stock.setFormattedChange(formattedChange);
            stock.setVolume(volume);
            stock.setFormattedVolume(formattedVolume);
            stock.setLastUpdated(System.currentTimeMillis());
            App.console().log(stock.isPriceUp());

            return stock;
        } catch (Exception e) {
            System.out.println("Error fetching data for " + ticker + ": " + e.getMessage());
            return null;
        }
    }

    private static String formatVolume(long volume) {
        if (volume >= 1_000_000) {
            return (volume / 1_000_000) + "M";
        } else if (volume >= 1_000) {
            return (volume / 1_000) + "K";
        }
        return String.valueOf(volume);
    }

    private static void startWebSocketUpdates() {
        try {
            WebSocketClient client = new WebSocketClient(new URI("wss://ws.finnhub.io?token=" + API_KEY)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("WebSocket opened");
                    stockMap.keySet().forEach(symbol -> send("{\"type\":\"subscribe\",\"symbol\":\"" + symbol + "\"}"));
                }

                @Override
                public void onMessage(String message) {
                    try {
                        JsonObject json = JsonParser.parseString(message).getAsJsonObject();
                        if (!"trade".equals(json.get("type").getAsString())) return;

                        JsonArray dataArray = json.getAsJsonArray("data");
                        for (JsonElement el : dataArray) {
                            JsonObject tick = el.getAsJsonObject();
                            String symbol = tick.get("s").getAsString();
                            double price = tick.get("p").getAsDouble();
                            latestPrices.put(symbol, price);
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing WebSocket message: " + e.getMessage());
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket closed: " + reason);
                    scheduler.shutdown();
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("WebSocket error: " + ex.getMessage());
                }
            };

            client.connect();

            scheduler.scheduleAtFixedRate(() -> {
                for (Map.Entry<String, Double> entry : latestPrices.entrySet()) {
                    String symbol = entry.getKey();
                    double price = entry.getValue();
                    StockData stock = stockMap.get(symbol);
                    if (stock != null) {
                        stock.setCurrentPrice(price);
                        stock.setLastUpdated(System.currentTimeMillis());
                        System.out.println("Updated " + symbol + " → $" + price);
                    }
                }
            }, 0, 3, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("Failed to start WebSocket: " + e.getMessage());
        }
    }

    public static class CompanyMeta {
        private String ticker;
        private String name;
        private String domain;

        public CompanyMeta(String ticker, String name, String domain) {
            this.ticker = ticker;
            this.name = name;
            this.domain = domain;
        }

        public String getTicker() { return ticker; }
        public String getName() { return name; }
        public String getDomain() { return domain; }
    }
} 
