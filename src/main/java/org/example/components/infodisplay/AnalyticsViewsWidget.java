package org.example.components.infodisplay;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.Icon;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class AnalyticsViewsWidget extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();

    public AnalyticsViewsWidget() {
        self.setDirection(FlexDirection.ROW)
            .setJustifyContent(FlexJustifyContent.BETWEEN)
            .setAlignment(FlexAlignment.CENTER)
            .setWidth("100%")
            .setStyle("background-color", "var(--dwc-surface-3)") 
            .setStyle("height", "70px");

        FlexLayout textContainer = new FlexLayout();
        textContainer.setDirection(FlexDirection.COLUMN)
                     .setAlignment(FlexAlignment.START)
                     .setJustifyContent(FlexJustifyContent.START)
                     .setMargin("10px")
                     .setSpacing("none");

        Paragraph titleText = new Paragraph("Total Views");
        titleText.setStyle("font-size", "8px")
                 .setStyle("font-weight", "bold")
                 .setStyle("color", "#9ba8af") 
                 .setStyle("text-transform", "uppercase");

        Paragraph valueText = new Paragraph("424,762");
        valueText.setStyle("font-size", "20px")
                 .setStyle("font-weight", "bold"); 

        textContainer.add(titleText, valueText);

        Icon icon = TablerIcon.create("caret-right");
        icon.setStyle("margin-right", "10px");

        self.add(textContainer, icon);
    }
}