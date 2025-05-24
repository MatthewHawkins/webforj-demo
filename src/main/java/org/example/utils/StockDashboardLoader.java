// package org.example.utils;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import com.webforj.App;
// import com.webforj.data.repository.CollectionRepository;
// import com.webforj.data.repository.Repository;
// import com.webforj.utilities.Assets;

// import java.util.ArrayList;
// import java.util.List;

// import org.example.model.StockData;
// import org.example.service.StockDataService;

// public final class StockDashboardLoader {

//   private StockDashboardLoader() {}

//   public static Repository<StockData> getStockData() {
//     // Load the list of CompanyMeta objects from the JSON
//     List<StockDataService.CompanyMeta> companyList = new Gson().fromJson(
//         Assets.contentOf(Assets.resolveContextUrl("context://static/data/companies.json")),
//         new TypeToken<List<StockDataService.CompanyMeta>>() {}
//     );

//     // Create a list to hold StockData
//     List<StockData> stockDataList = new ArrayList<>();

//     StockDataService service = new StockDataService();

//     for (StockDataService.CompanyMeta company : companyList) {
//       StockData data = service.fetchStockData(company.getTicker(), company.getName(), company.getDomain());

//       if (data != null) {
//         stockDataList.add(data);
//       }

//       // Optional: wait to respect Finnhub rate limits
//       try {
//         Thread.sleep(1000); // 1 second
//       } catch (InterruptedException e) {
//         Thread.currentThread().interrupt();
//       }
//     }

//     App.console().log(stockDataList.size());
//     return new CollectionRepository<>(stockDataList);
//   }
// }
