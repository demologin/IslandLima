package com.javarush.island.parfenov.view;

import javax.swing.*;
import java.util.Map;

public class CButton extends JButton {

    public CButton(GameScreen gameScreen) {
        Map<String, ImageIcon> icons = gameScreen.getIcons();
        ImageIcon imageIcon = icons.get("Empty");
        setIcon(imageIcon);
    }

    public void changeImage(ImageIcon imageIcon) {
        this.setIcon(imageIcon);
        this.revalidate();
    }
}
