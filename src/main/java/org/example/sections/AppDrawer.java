package org.example.sections;

import org.example.components.appdrawer.Badge;
import org.example.components.appdrawer.Badge.Variant;
import org.example.sections.appdrawer.DrawerProgress;
import org.example.views.HomeView;

import com.webforj.component.Composite;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.appnav.AppNav;
import com.webforj.component.layout.appnav.AppNavItem;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class AppDrawer extends Composite<FlexLayout>{

  private FlexLayout self = getBoundComponent();

  public AppDrawer(){
    self.setDirection(FlexDirection.COLUMN);

    setHeader();
  }

  private void setHeader(){
    AppNav appNav = new AppNav();
    appNav.addItem(new AppNavItem("Home", HomeView.class, TablerIcon.create("home")).setSuffixComponent(new Badge("6", Variant.SUCCESS, true, true)));
    self.add(appNav);
    self.add(new DrawerProgress());
  }
  
}
