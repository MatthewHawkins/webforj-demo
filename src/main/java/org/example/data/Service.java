package org.example.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import com.webforj.data.repository.CollectionRepository;
import com.webforj.data.repository.Repository;
import com.webforj.utilities.Assets;

public final class Service {

  private Service() {}

  public static Repository<SalesEntry> getSalesEntries() {
    List<SalesEntry> data =
        new Gson().fromJson(Assets.contentOf(Assets.resolveContextUrl("context://static/data.json")),
            new TypeToken<List<SalesEntry>>() {});

    return new CollectionRepository<>(data);
  }
}