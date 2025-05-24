// package org.example.utils;


// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// import org.example.model.StockData;
// import org.example.service.StockDataService;

// public class TestStockDataService {
//     public static void main(String[] args) {
//         // Create the service
//         StockDataService service = new StockDataService();

//         // Define Apple company metadata
//         StockDataService.CompanyMeta apple = new StockDataService.CompanyMeta(
//             "AAPL", "Apple Inc.", "apple.com"
//         );

//         // Fetch stock data for Apple
//         StockData appleData = service.fetchStockData(
//             apple.getTicker(), apple.getName(), apple.getDomain()
//         );

//         // Print results to verify
//         if (appleData != null) {
//             System.out.println("=== Apple Stock Data ===");
//             System.out.println("Company: " + appleData.getCompanyName());
//             System.out.println("Ticker: " + appleData.getTicker());
//             System.out.println("Logo URL: " + appleData.getLogoUrl());
//             System.out.println("Current Price: $" + appleData.getCurrentPrice());
//             System.out.println("Previous Close: $" + appleData.getPreviousClose());
//             System.out.println("Change: " + appleData.getFormattedChange());
//             System.out.println("Volume: " + appleData.getFormattedVolume());
//             System.out.println("Updated: " + appleData.getLastUpdated());
//         } else {
//             System.out.println("Failed to fetch data for Apple.");
//         }
//     }
// }
