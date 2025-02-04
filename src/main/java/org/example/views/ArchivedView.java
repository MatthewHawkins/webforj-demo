package org.example.views;

import org.example.components.Explore;

import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route(value = "/archived", outlet = MainLayout.class)
@FrameTitle("Archived")
public class ArchivedView extends Composite<FlexLayout> {
  private FlexLayout self = getBoundComponent();

  public ArchivedView() {
    self.setHeight("100%");
    self.setAlignment(FlexAlignment.CENTER);
    self.add(new Explore("Archived"));
  }
}
