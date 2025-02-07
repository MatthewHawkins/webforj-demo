package org.example.sections.appdrawer;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H4;
import com.webforj.component.html.elements.Img;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class DrawerFooter extends Composite<FlexLayout> {
  
  private FlexLayout self = getBoundComponent();
  
  public DrawerFooter(){
    // Img logo = new Img("ws://webforj_icon.svg");
    // self.setJustifyContent(FlexJustifyContent.AROUND);
    self.setSpacing("1.25em");
    self.setAlignment(FlexAlignment.CENTER);

    Img logo = new Img("ws://webforj_icon.svg");
    logo.setStyle("display", "block");
    logo.setHeight(35);
    
    H4 title = new H4("webforJ");
    Paragraph subtitle = new Paragraph("ver. 24.21");
    subtitle.setStyle("color", "#adadad");
    self.setWidth("100%");
    self.setStyle("border-top", "#adadad 1px solid");
    Div text = new Div(title, subtitle);
    self.add(logo, text);
  }

}
