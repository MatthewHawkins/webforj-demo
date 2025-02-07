package org.example.components.infodisplay;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Img;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.html.elements.Strong;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.layout.flexlayout.FlexAlignment;

public class UserCardWidget extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();
    private final Img avatar;
    private final Strong username;
    private final Paragraph position;

    public UserCardWidget(String name, String role, String avatarUrl) {
        self.setDirection(FlexDirection.ROW)
            .setAlignment(FlexAlignment.CENTER)
            .setSpacing("none")
            .setStyle("border-radius", "8px")
            .setStyle("color", "white")
            .setStyle("background-color", "var(--dwc-surface-3)") 
            .setWidth("100%");

        avatar = new Img(avatarUrl, "User Avatar");
        avatar.setStyle("width", "30px")
              .setStyle("height", "30px")
              .setStyle("border-radius", "50%");

        username = new Strong(name);
        username.setStyle("font-size", "12px");

        position = new Paragraph(role);
        position.setStyle("font-size", "10px")
                .setStyle("color", "#a0a0a0");

        FlexLayout textContainer = new FlexLayout();
        textContainer.setDirection(FlexDirection.COLUMN)
                     .setSpacing("2px");
        textContainer.add(username, position);

        self.add(avatar, textContainer);
    }
}