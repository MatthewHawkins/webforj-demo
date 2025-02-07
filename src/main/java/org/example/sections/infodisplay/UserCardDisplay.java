package org.example.sections.infodisplay;

import org.example.components.infodisplay.UserCardWidget;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class UserCardDisplay extends Composite<FlexLayout> {

    private final FlexLayout self = getBoundComponent();

    public UserCardDisplay() {
        self.setDirection(FlexDirection.COLUMN)
            .setSpacing("10px")
            .setStyle("width", "100%");

        Paragraph title = new Paragraph("Frequent Users");
        title.setStyle("font-size", "12px")
             .setStyle("font-weight", "bold")
             .setStyle("text-transform", "uppercase")
             .setStyle("color", "#9ba8af") 
             .setStyle("padding", "10px 15px");

        Div userCardWrapper = new Div();
        userCardWrapper.setStyle("display", "flex")
                       .setStyle("flex-direction", "column")
                       .setStyle("gap", "10px");

        String[] names = {"Telma Fridley", "Chandler Hervieux", "Percy Demers", "Antoine Masson"};
        String[] positions = {"Admin User", "Manager", "Director", "Premium User"};

        for (int i = 0; i < names.length; i++) {
            userCardWrapper.add(new UserCardWidget(names[i], positions[i], 
                "https://coderthemes.com/ubold/layouts/default/assets/images/users/user-" + (i + 1) + ".jpg"));
        }

        // Add title and user cards to the layout
        self.add(title, userCardWrapper);
    }
}
