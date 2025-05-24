package org.example.components.home;

import java.util.List;

import org.example.model.StockData;
import org.example.service.StockDataService;
import org.example.utils.renderers.CompanyRenderer;
import org.example.utils.renderers.PriceRenderer;

import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.component.tabbedpane.event.TabSelectEvent;
import com.webforj.component.table.Table;
import com.webforj.data.repository.CollectionRepository;
import com.webforj.data.repository.Repository;
import com.webforj.utilities.Assets;
import com.webforj.component.table.Column.SortDirection;

public class StocksTable extends Composite<FlexLayout> {

  FlexLayout self = getBoundComponent();

  TabbedPane sortOptions = new TabbedPane();
  Table<StockData> table = new Table<>();

  public StocksTable() {

    String json = Assets.contentOf(Assets.resolveContextUrl("context://static/data/companies.json"));
    List<StockData> stockList = StockDataService.initializeFromJson(json);
    Repository<StockData> stockRepo = new CollectionRepository<>(stockList);
    table.setRepository(stockRepo);

    getBoundComponent().setHeight("100%");
    getBoundComponent().setWidth("100%");
    getBoundComponent().setDirection(FlexDirection.COLUMN);
    getBoundComponent().setStyle("background-color", "var(--dwc-surface-3)");

    table.addClassName("home-view__table");
    table.addColumn("Domain", StockData::getCompanyDomain).setHidden(true);
    table.addColumn("Name", StockData::getCompanyName).setHidden(true);
    table.addColumn("Ticker", StockData::getTicker).setHidden(true);
    table.addColumn("Company", new CompanyRenderer());
    table.addColumn("Price", StockData::getCurrentPrice).setHidden(true);
    table.addColumn("IsUp", StockData::isPriceUp).setHidden(true);
    table.addColumn("Current Price", new PriceRenderer());
    table.setClientSorting(true);
    // sortOptions.addTab("ALL");
    // sortOptions.addTab("HIGHEST");
    // sortOptions.addTab("LOWEST");
    // sortOptions.onSelect(this::tableFilter);
    // sortOptions.setBorderless(true);
    // sortOptions.setBodyHidden(true);
    // sortOptions.setHideActiveIndicator(true);

    getBoundComponent().add(sortOptions, table);
  }

  private void tableFilter(TabSelectEvent e) {
    SortDirection sortDirection = switch (e.getTabIndex()) {
      case 0 -> SortDirection.NONE;
      case 1 -> SortDirection.DESC;
      case 2 -> SortDirection.ASC;
      default -> null;
    };

    if (sortDirection != null) {
      table.getColumnById("Revenue").setSortDirection(sortDirection);
      table.getRepository().commit();
    }
  }
}