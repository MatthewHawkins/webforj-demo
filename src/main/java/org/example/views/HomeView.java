package org.example.views;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.H1;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route(value = "/*", outlet = MainLayout.class)
@FrameTitle("Home")
public class HomeView extends Composite<FlexLayout>{

  private FlexLayout self = getBoundComponent();

  public HomeView(){
    self.setHeight("100%");
    self.setWidth("100%");
    self.setAlignment(FlexAlignment.CENTER);
    self.setDirection(FlexDirection.COLUMN);
    self.add(new H1("Placeholder"));
  }
}
