package org.example.sections.home;

import org.example.data.SalesEntry;
import org.example.data.Service;
import org.example.utils.HighLowRenderer;

import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.component.tabbedpane.event.TabSelectEvent;
import com.webforj.component.table.Table;
import com.webforj.component.table.Column.SortDirection;

public class SalesTable extends Composite<FlexLayout>{
  
  FlexLayout self = getBoundComponent();

  TabbedPane sortOptions = new TabbedPane();
  Table<SalesEntry> table = new Table<>();

  public SalesTable(){
    getBoundComponent().setHeight("100%");
    getBoundComponent().setWidth("100%");
    getBoundComponent().setDirection(FlexDirection.COLUMN);

    table.setRepository(Service.getSalesEntries());
    table.addClassName("home-view__table");
    table.addColumn("Growth", SalesEntry::getRanking).setRenderer(new HighLowRenderer());
    table.addColumn("Company", SalesEntry::getCompany);
    table.addColumn("Revenue", SalesEntry::getTotalRevenue).setSortable(true);
    App.console().log(table.getColumns().getLast().getId());
    table.getColumns().getLast().setSortDirection(SortDirection.ASC);

    sortOptions.addTab("ALL");
    sortOptions.addTab("HIGHEST");
    sortOptions.addTab("LOWEST");
    sortOptions.onSelect(this::tableFilter);
    sortOptions.setBorderless(true);
    sortOptions.setBodyHidden(true);
    sortOptions.setHideActiveIndicator(true);

    getBoundComponent().add(sortOptions, table);
  }

  private void tableFilter(TabSelectEvent e){
    if(e.getTabIndex() == 0){
      // nothing
    } else if(e.getTabIndex() == 1){
      // table.getColumnById("Revenue").setSortDirection(SortDirection.DESC);
      table.getColumns().getLast().setSortDirection(SortDirection.DESC);
      App.console().log("HIGH");
    } else if(e.getTabIndex() == 2){
      // table.getColumnById("Revenue").setSortDirection(SortDirection.ASC);
      table.getColumns().getLast().setSortDirection(SortDirection.ASC);
      App.console().log("LOW");

    }
  }

}
