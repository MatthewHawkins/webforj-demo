package org.example.sections.appdrawer;

import org.example.components.appdrawer.ProgressRing;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class DrawerProgress extends Composite<FlexLayout>{
  
  FlexLayout self = getBoundComponent();

  ProgressRing ringOne = new ProgressRing(41, "Sales");
  Paragraph textOne = new Paragraph("Sales");
  ProgressRing ringTwo = new ProgressRing(63, "Leads");
  Paragraph textTwo = new Paragraph("Leads");

  FlexLayout progressOne = FlexLayout.create(ringOne, textOne).vertical().align().center().build();
  FlexLayout progressTwo = FlexLayout.create(ringTwo, textTwo).vertical().align().center().build();
  
  public DrawerProgress(){
    self.add(progressOne, progressTwo);
    self.setJustifyContent(FlexJustifyContent.CENTER);
    self.addClassName("app-drawer__progress");
  }
}
