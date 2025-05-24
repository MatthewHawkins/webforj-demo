package org.example.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.example.model.StockData;

public class TestLiveWebSocket {
    public static void main(String[] args) throws Exception {
        Map<String, StockData> stockMap = new ConcurrentHashMap<>();

        stockMap.put("AAPL", new StockData());
        stockMap.get("AAPL").setTicker("AAPL");
        stockMap.get("AAPL").setCompanyName("Apple Inc.");
        stockMap.get("AAPL").setCompanyDomain("apple.com");

        // Add other stocks similarly or load from JSON
        FinnhubWebSocketClient client = new FinnhubWebSocketClient("d0obt2pr01qsib2blql0d0obt2pr01qsib2blqlg", stockMap);
        client.connect();
    }
}
