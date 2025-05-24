package org.example.views;


import org.example.components.home.StocksTable;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Img;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route(value = "/*", outlet = MainLayout.class)
@FrameTitle("Home")
public class HomeView extends Composite<FlexLayout>{

  private FlexLayout self = getBoundComponent();
  private Img coverImage = new Img("ws://city.jpg");
  private Div spacer = new Div();

  public HomeView(){
    self.setHeight("100%");
    self.setWidth("100%");
    self.setAlignment(FlexAlignment.CENTER);
    self.setDirection(FlexDirection.COLUMN);
    spacer.addClassName("home-view__cover");
    self.add(spacer);

// StockDataService service = new StockDataService();
// String json = Assets.contentOf(Assets.resolveContextUrl("context://static/data/companies.json"));
// List<StockData> data = service.initializeFromJson(json);

    // self.add(new SalesTable());
    self.add(new StocksTable());
  }
}
