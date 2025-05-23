package org.example.sections;

import org.example.components.appdrawer.Badge;
import org.example.components.appdrawer.Badge.Variant;
import org.example.sections.appdrawer.DrawerProgress;
import org.example.views.HomeView;

import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.appnav.AppNav;
import com.webforj.component.layout.appnav.AppNavItem;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.optioninput.CheckBox;

public class AppDrawer extends Composite<FlexLayout>{

  private FlexLayout self = getBoundComponent();

  public AppDrawer(){
    self.setDirection(FlexDirection.COLUMN);

    setHeader();
  }

  private void setHeader(){
    AppNav appNav = new AppNav();
    appNav.addItem(new AppNavItem("Home", HomeView.class, TablerIcon.create("home")));
    appNav.addItem(new AppNavItem("Alerts", "/home/alerts", TablerIcon.create("alert-hexagon")).setSuffixComponent(new Badge("4", Variant.DANGER, true, true)));
    appNav.addItem(new AppNavItem("Metrics", "/home/metrics", TablerIcon.create("graph")));
    appNav.addItem(new AppNavItem("Reports", "/home/reports", TablerIcon.create("report")));
    appNav.addItem(new AppNavItem("Leads", "/home/leads", TablerIcon.create("device-ipad-star")).setSuffixComponent(new Badge("63", Variant.PRIMARY, true, true)));
    self.add(appNav);
    self.add(new DrawerProgress());
  }
  
}
