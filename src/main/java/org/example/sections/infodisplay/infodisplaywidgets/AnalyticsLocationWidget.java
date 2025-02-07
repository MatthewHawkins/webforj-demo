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

public class AnalyticsLocationWidget extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();
    private final GoogleChart chart = new GoogleChart(GoogleChart.Type.GEO);

    public AnalyticsLocationWidget() {
        self.setDirection(FlexDirection.ROW)
            .setJustifyContent(FlexJustifyContent.BETWEEN)
            .setAlignment(FlexAlignment.CENTER)
            .setWidth("100%")
            .setStyle("background-color", "var(--dwc-surface-3)") 
            .setStyle("color", "white")
            .setStyle("cursor", "pointer")
            .setStyle("height", "70px");

        Paragraph titleText = new Paragraph("Top Locations");
        titleText.setStyle("font-size", "8px")
                 .setStyle("font-weight", "bold")
                 .setStyle("text-transform", "uppercase");

        Map<String, Object> options = new HashMap<>();
        options.put("colors", List.of("#ffa052", "#0284c7"));
        options.put("backgroundColor", "transparent");
        options.put("datalessRegionColor", "transparent"); 
        options.put("defaultColor", "white"); 
        options.put("legend", "none"); 
        options.put("displayMode", "regions"); 

        chart.setOptions(options);
        chart.setStyle("width", "70px") 
             .setStyle("height", "35px");

        List<Object> data = new ArrayList<>();
        data.add(List.of("Country", "Views")); 

        String[] countries = {"Germany", "United States", "Brazil", "Canada", "France", "RU"};
        for (String country : countries) {
            data.add(List.of(country, Math.random() * 10000)); 
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