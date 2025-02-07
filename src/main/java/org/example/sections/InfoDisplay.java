package org.example.sections;

import org.example.sections.infodisplay.InfoDisplayAnalytics;
import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class InfoDisplay extends Composite<FlexLayout> {

  FlexLayout self = getBoundComponent();

  public InfoDisplay(){
    configureLayout();
  }
  
  public void configureLayout(){
    // Set flex properties
    self.setDirection(FlexDirection.COLUMN);

    // Set other properties
    self.setWidth("calc(var(--dwc-app-layout-drawer-width) + 10px)");
    self.setHeight("100%");
    self.addClassName("info-display");

    self.add(new InfoDisplayAnalytics());
  }
}
