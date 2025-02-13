package org.example.sections.infodisplay;

import org.example.components.infodisplay.CardWidget;
import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.IconButton;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class MyCardsDisplay extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();

    public MyCardsDisplay() {
        self.setDirection(FlexDirection.COLUMN)
            .setJustifyContent(FlexJustifyContent.START)
            .setAlignment(FlexAlignment.CENTER)
            .setSpacing("8px")
            .setStyle("margin-top", "2px")
            .setStyle("background", "var(--dwc-surface-3)")
            .setStyle("box-shadow", "0 4px 6px rgba(0, 0, 0, 0.15)")
            .setWidth("100%")
            .setHeight("100%");

        FlexLayout header = new FlexLayout();
        header.setDirection(FlexDirection.ROW)
              .setJustifyContent(FlexJustifyContent.BETWEEN)
              .setAlignment(FlexAlignment.CENTER)
              .setStyle("margin", "10px")
              .setWidth("90%");

        Paragraph title = new Paragraph("My Cards");
        title.setStyle("font-size", "14px")
             .setStyle("font-weight", "bold");

        IconButton seeAllButton = new IconButton(TablerIcon.create("arrow-up-right"));

        header.add(title, seeAllButton);

        CardWidget primaryCard = new CardWidget("VISA", "**** **** **** 2104", "$ 4,540.20");
        CardWidget secondaryCard = new CardWidget("Mastercard", "**** **** **** 3309", "$ 1,230.50");

        FlexLayout cardsWrapper = new FlexLayout();
        cardsWrapper.setDirection(FlexDirection.COLUMN)
                    .setJustifyContent(FlexJustifyContent.CENTER)
                    .setAlignment(FlexAlignment.CENTER)
                    .setSpacing("5px")
                    .setStyle("width", "85%")
                    .setStyle("max-width", "250px")
                    .setStyle("position", "relative")
                    .setStyle("margin", "0 auto");

        secondaryCard.getContent()
                     .setStyle("position", "absolute")
                     .setStyle("top", "10px")
                     .setStyle("left", "0")
                     .setStyle("opacity", "0.75")
                     .setStyle("filter", "brightness(85%)")
                     .setStyle("width", "100%")
                     .setStyle("z-index", "0");

        primaryCard.getContent()
                   .setStyle("position", "relative")
                   .setStyle("width", "100%")
                   .setStyle("z-index", "1")
                   .setStyle("transition", "transform 0.2s ease-in-out");

        primaryCard.getContent().setAttribute("onmouseover", "this.style.transform='translateY(-5px)'");
        primaryCard.getContent().setAttribute("onmouseout", "this.style.transform='translateY(0px)'");

        cardsWrapper.add(secondaryCard, primaryCard);

        self.add(header, cardsWrapper);
    }
}