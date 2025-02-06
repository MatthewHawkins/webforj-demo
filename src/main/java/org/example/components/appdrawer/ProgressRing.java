package org.example.components.appdrawer;

import com.webforj.annotation.Attribute;
import com.webforj.annotation.JavaScript;
import com.webforj.annotation.StyleSheet;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.PropertyDescriptor;
import com.webforj.component.element.annotation.NodeName;
import com.webforj.component.html.elements.Paragraph;

@JavaScript(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/shoelace-autoloader.js", attributes = {
      @Attribute(name = "type", value = "module") })
      @StyleSheet(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/themes/light.css")
        @StyleSheet(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/themes/dark.css")
@NodeName("sl-progress-ring")
public class ProgressRing extends ElementComposite{
  
  private final PropertyDescriptor<Integer> value = PropertyDescriptor.property("value", 0);
  private final PropertyDescriptor<String> label = PropertyDescriptor.property("label", "");

  public ProgressRing(){

  }

  public ProgressRing(Integer value, String label){
    set(this.value, value);
    set(this.label, label);
    getBoundComponent().add(new Paragraph(value.toString()));
  }

  public void setValue(Integer val){
    set(this.value, val);
  }
}
