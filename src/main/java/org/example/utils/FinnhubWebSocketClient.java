package org.example.utils;


import com.google.gson.*;
import org.example.model.StockData;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.*;
import java.util.concurrent.*;

public class FinnhubWebSocketClient extends WebSocketClient {

    private final Map<String, StockData> stockMap;
    private final Map<String, Double> latestPrices = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final Gson gson = new Gson();

    public FinnhubWebSocketClient(String apiKey, Map<String, StockData> stockMap) throws Exception {
        super(new URI("wss://ws.finnhub.io?token=" + apiKey));
        this.stockMap = stockMap;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("WebSocket opened");
        stockMap.keySet().forEach(symbol -> {
            send("{\"type\":\"subscribe\",\"symbol\":\"" + symbol + "\"}");
        });

        // Every 3 seconds, update StockData objects with latest prices
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

                latestPrices.put(symbol, price); // buffer until next update window
            }

        } catch (Exception e) {
            System.out.println("Error parsing message: " + e.getMessage());
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
}
