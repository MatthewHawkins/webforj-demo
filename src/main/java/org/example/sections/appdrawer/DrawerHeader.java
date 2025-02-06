package org.example.sections.appdrawer;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.H3;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class DrawerHeader extends Composite<FlexLayout> {
  private FlexLayout self = getBoundComponent();

  public DrawerHeader() {
    self.addClassName("app-drawer__header");

    H3 title = new H3("My App");
    title.setStyle("margin-bottom", "0");
    title.setStyle("padding-left", "14px");
    self.add(title);
  }
}
