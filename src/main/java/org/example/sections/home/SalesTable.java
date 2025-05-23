package org.example.sections.home;

import org.example.data.SalesEntry;
import org.example.data.Service;
import org.example.utils.CompanyRenderer;
import org.example.utils.HighLowRenderer;

import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.component.tabbedpane.event.TabSelectEvent;
import com.webforj.component.table.Table;
import com.webforj.component.table.Column.SortDirection;

public class SalesTable extends Composite<FlexLayout> {

  FlexLayout self = getBoundComponent();

  TabbedPane sortOptions = new TabbedPane();
  Table<SalesEntry> table = new Table<>();

  public SalesTable() {
    getBoundComponent().setHeight("100%");
    getBoundComponent().setWidth("100%");
    getBoundComponent().setDirection(FlexDirection.COLUMN);
    getBoundComponent().setStyle("background-color", "var(--dwc-surface-3)");

    table.setRepository(Service.getSalesEntries());
    table.addClassName("home-view__table");
    table.addColumn("Growth", SalesEntry::getRanking).setRenderer(new HighLowRenderer());
    table.addColumn("Name", SalesEntry::getCompany).setHidden(true);
    table.addColumn("Product", SalesEntry::getProduct).setHidden(true);
    // table.addColumn("Company and Product", );
    table.addColumn("Company", SalesEntry::getCompany).setHidden(true);
    table.addColumn("Company", SalesEntry::getCompany).setRenderer(new CompanyRenderer());
    table.addColumn("Revenue", SalesEntry::getTotalRevenue).setSortable(true);
    App.console().log(table.getColumns().getLast().getId());
    table.setClientSorting(true);


    sortOptions.addTab("ALL");
    sortOptions.addTab("HIGHEST");
    sortOptions.addTab("LOWEST");
    sortOptions.onSelect(this::tableFilter);
    sortOptions.setBorderless(true);
    sortOptions.setBodyHidden(true);
    sortOptions.setHideActiveIndicator(true);

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
