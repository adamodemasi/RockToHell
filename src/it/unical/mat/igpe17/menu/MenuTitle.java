package it.unical.mat.igpe17.menu;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MenuTitle extends Pane {
    private Text text;

    public MenuTitle(String name) {
        String title = "";
        for (char c : name.toCharArray()) {
            title += c + " ";
        }

        text = new Text(title);
        text.setFont(Font.font ("Taranis", 50)  );
        text.setFill(Color.WHITESMOKE);
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}