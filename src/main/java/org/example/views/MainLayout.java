package org.example.views;

import java.util.Set;

import org.example.sections.AppDrawer;
import org.example.sections.InfoDisplay;
import org.example.sections.appdrawer.DrawerFooter;
import org.example.sections.appdrawer.DrawerHeader;

import com.webforj.component.Component;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.H1;
import com.webforj.component.layout.applayout.AppDrawerToggle;
import com.webforj.component.layout.applayout.AppLayout;
import com.webforj.component.layout.toolbar.Toolbar;
import com.webforj.router.Router;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import com.webforj.router.event.NavigateEvent;

@Route
public class MainLayout extends Composite<AppLayout> {
  private AppLayout self = getBoundComponent();
  private H1 title = new H1();
  private InfoDisplay infoDisplay = new InfoDisplay();

  public MainLayout() {
    setHeader();
    setDrawer();
    setInfoDisplay();
    Router.getCurrent().onNavigate(this::onNavigate);
  }

  private void setHeader() {

    Toolbar toolbar = new Toolbar();
    toolbar.addToStart(new AppDrawerToggle());
    toolbar.addToTitle(title);

    self.addToHeader(toolbar);
  }

  private void setDrawer() {
    self.setDrawerHeaderVisible(true);
    self.setDrawerFooterVisible(true);
    self.addToDrawerTitle(new DrawerHeader());
    self.addToDrawer(new AppDrawer());
    self.addToDrawerFooter(new DrawerFooter());
  }

  private void setInfoDisplay(){
    self.add(infoDisplay);
  }

  private void onNavigate(NavigateEvent ev) {
    Set<Component> components = ev.getContext().getAllComponents();
    Component view = components.stream().filter(c -> c.getClass().getSimpleName().endsWith("View")).findFirst()
        .orElse(null);

    if (view != null) {
      FrameTitle frameTitle = view.getClass().getAnnotation(FrameTitle.class);
      title.setText(frameTitle != null ? frameTitle.value() : "");
    }
  }
}
