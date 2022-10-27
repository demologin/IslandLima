package com.javarush.island.parfenov.view;

import com.javarush.island.parfenov.settings.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RightPanel extends JPanel {
    private final Map<String, String> nameToUnicode = new HashMap<>();
    private final Map<String, JLabel> nameToLabel = new HashMap<>();
    
    public RightPanel(GameSettings gameSettings) {
        setLayout(new GridLayout(0, 1));
        Class<?>[] organismClasses = gameSettings.getOrganismClasses();
        String[] icons = gameSettings.getIcons();
        for (int i = 0; i < organismClasses.length; i++) {
            JLabel jLabel = new JLabel();
            String name = organismClasses[i].getSimpleName();
            nameToLabel.put(name, jLabel);
            nameToUnicode.put(name, icons[i]);
            jLabel.setText(icons[i] + "hello everyone");
            jLabel.setFont (jLabel.getFont ().deriveFont (17.0f));
            add(jLabel);
        }
    }

    public Map<String, String> getNameToUnicode() {
        return nameToUnicode;
    }

    public Map<String, JLabel> getNameToLabel() {
        return nameToLabel;
    }

}
