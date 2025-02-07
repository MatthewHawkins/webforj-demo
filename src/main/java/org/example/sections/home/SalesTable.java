package org.example.sections.home;

import org.example.data.SalesEntry;
import org.example.data.Service;

import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.component.table.Table;

public class SalesTable extends Composite<FlexLayout>{
  
  FlexLayout self = getBoundComponent();

  TabbedPane sortOptions = new TabbedPane();
  Table<SalesEntry> table = new Table<>();

  public SalesTable(){
    table.setRepository(Service.getSalesEntries());
    table.setHeight("500px");

    table.addColumn("Expected", SalesEntry::getRanking);
    getBoundComponent().add(table);
  }

}
