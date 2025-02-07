package org.example.sections.infodisplay;

import org.example.components.infodisplay.AnalyticsLocationWidget;
import org.example.components.infodisplay.AnalyticsTrendWidget;
import org.example.components.infodisplay.AnalyticsUsageWidget;
import org.example.components.infodisplay.AnalyticsViewsWidget;

import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class InfoDisplayAnalytics extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();

    public InfoDisplayAnalytics() {
        self.setDirection(FlexDirection.COLUMN)
            .setJustifyContent(FlexJustifyContent.START)
            .setAlignment(FlexAlignment.START)
            .setSpacing("2px")
            .setStyle("color", "white");

        self.add(
            new AnalyticsViewsWidget(), 
            new AnalyticsLocationWidget(),
            new AnalyticsTrendWidget(),
            new AnalyticsUsageWidget()
        );
    }
}