package org.example.sections.infodisplay.infodisplaywidgets;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.icons.Icon;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.googlecharts.GoogleChart;

import java.util.*;

public class AnalyticsTrendWidget extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();
    private final GoogleChart chart = new GoogleChart(GoogleChart.Type.LINE);

    public AnalyticsTrendWidget() {
        self.setDirection(FlexDirection.ROW)
            .setJustifyContent(FlexJustifyContent.BETWEEN)
            .setAlignment(FlexAlignment.CENTER)
            .setWidth("100%")
            .setStyle("background-color", "var(--dwc-surface-3)") 
            .setStyle("cursor", "pointer")
            .setStyle("height", "70px");

        Paragraph titleText = new Paragraph("Monthly Trend");
        titleText.setStyle("font-size", "8px")
                 .setStyle("font-weight", "bold")
                 .setStyle("text-transform", "uppercase");

        Map<String, Object> options = new HashMap<>();
        options.put("colors", List.of("white"));
        options.put("backgroundColor", "transparent");
        options.put("chartArea", Map.of("width", "80%", "height", "70%")); 
        options.put("legend", "none"); 
        options.put("tooltip", Map.of("trigger", "none"));
        options.put("hAxis", Map.of("textStyle", Map.of("color", "white"))); 
        options.put("vAxis", Map.of("textStyle", Map.of("color", "white"), "minValue", 0));
        options.put("hAxis", Map.of(
            "textStyle", Map.of("color", "transparent"), 
            "baselineColor", "transparent",
            "gridlines", Map.of("color", "transparent")
        ));
        options.put("vAxis", Map.of(
        "textStyle", Map.of("color", "transparent"),
        "baselineColor", "transparent",
        "gridlines", Map.of("color", "transparent") 
        ));

        chart.setOptions(options);
        chart.setStyle("width", "125px")
             .setStyle("height", "40px");

        List<Object> data = new ArrayList<>();
        data.add(List.of("Month", "Views")); 
     
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (String month : months) {
                 data.add(List.of(month, Math.random() * 10000)); 
        }
        chart.setData(data);

        Icon icon = TablerIcon.create("caret-right");
        icon.setStyle("margin-right", "10px");
        icon.setStyle("color", "white");

        FlexLayout textAndChart = new FlexLayout();
        textAndChart.setDirection(FlexDirection.COLUMN)
                    .setMargin("5px")
                    .setSpacing("2px");
        textAndChart.add(titleText, chart);

        self.add(textAndChart, icon);
    }
}