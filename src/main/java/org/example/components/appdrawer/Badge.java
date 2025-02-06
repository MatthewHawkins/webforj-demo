package org.example.components.appdrawer;

import com.webforj.annotation.Attribute;
import com.webforj.annotation.JavaScript;
import com.webforj.annotation.StyleSheet;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.PropertyDescriptor;
import com.webforj.component.element.annotation.NodeName;
import com.webforj.component.html.elements.Div;

@JavaScript(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/shoelace-autoloader.js", attributes = {
    @Attribute(name = "type", value = "module") })
@StyleSheet(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/themes/light.css")
@StyleSheet(value = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.20.0/cdn/themes/dark.css")
@NodeName("sl-badge")
public class Badge extends ElementComposite {

  public enum Variant {
    PRIMARY("primary"),
    SUCCESS("success"),
    NEUTRAL("neutral"),
    WARNING("warning"),
    DANGER("danger");

    private final String value;

    Variant(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return value;
    }

  }

  private final PropertyDescriptor<String> variant = PropertyDescriptor.property("variant", Variant.PRIMARY.toString());
  private final PropertyDescriptor<Boolean> pill = PropertyDescriptor.property("pill", false);
  private final PropertyDescriptor<Boolean> pulse = PropertyDescriptor.property("pulse", false);
  private Div value = new Div();

  public Badge() {
    getBoundComponent().add(value);
  }

  public Badge(String val, Variant vari, Boolean isPulse, Boolean isPill) {
    getBoundComponent().add(value);
    setValue(val);
    setVariant(vari);
    setPulse(isPulse);
    setPill(isPill);
  }

  public void setValue(String value) {
    this.value.setText(value);
  }

  public void setVariant(Variant newVariant) {
    set(variant, newVariant.toString());
  }

  public void setPulse(Boolean isPulse) {
    set(pulse, isPulse);
  }

  public void setPill(Boolean isPill) {
    set(pill, isPill);
  }
}
