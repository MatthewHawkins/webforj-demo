package org.example.components.infodisplay;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.html.elements.Strong;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.layout.flexlayout.FlexAlignment;

public class CardWidget extends Composite<FlexLayout> {

    private final FlexLayout self;

    public CardWidget(String brand, String cardNumber, String balance) {
        self = getContent();

        self.setDirection(FlexDirection.COLUMN)
            .setMargin("10px")
            .setSpacing("0")
            .setStyle("border-radius", "10px")
            .setStyle("background-color", "#034388")
            .setStyle("max-width", "200px") 
            .setStyle("height", "120px");

        Paragraph brandText = new Paragraph(brand);
        brandText.setStyle("font-size", "12px")
                 .setStyle("margin", "10px")
                 .setStyle("font-weight", "bold");

        Paragraph cardNumberText = new Paragraph(cardNumber);
        cardNumberText.setStyle("font-size", "14px").setStyle("margin", "10px");

        Paragraph balanceText = new Paragraph(balance);
        balanceText.setStyle("font-size", "16px")
                    .setStyle("font-weight", "bold")
                    .setStyle("margin", "10px");

        self.add(brandText, cardNumberText, balanceText);
    }

    public FlexLayout getContent() {
        return getBoundComponent();
    }
}