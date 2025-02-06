package org.example.views;

import java.util.Set;

import org.example.components.DrawerHeader;
import org.example.sections.InfoDisplay;

import com.webforj.component.Component;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.H1;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.applayout.AppDrawerToggle;
import com.webforj.component.layout.applayout.AppLayout;
import com.webforj.component.layout.appnav.AppNav;
import com.webforj.component.layout.appnav.AppNavItem;
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
    self.setDrawerHeaderVisible(true);

    self.addToDrawerTitle(new DrawerHeader());

    Toolbar toolbar = new Toolbar();
    toolbar.addToStart(new AppDrawerToggle());
    toolbar.addToTitle(title);

    self.addToHeader(toolbar);
  }

  private void setDrawer() {

    AppNav appNav = new AppNav();
    appNav.addItem(new AppNavItem("Home", HomeView.class, TablerIcon.create("home")));

    self.addToDrawer(appNav);
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
