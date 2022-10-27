package com.javarush.island.parfenov.view;

import com.javarush.island.parfenov.gameMechanics.Cell;
import com.javarush.island.parfenov.gameMechanics.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GameScreen extends JPanel {
    private final Cell[][] field;
    private final Map<Cell, CButton> buttons = new HashMap<>();
    private final Map<String, BufferedImage> images = new HashMap<>();
    private final Map<String, ImageIcon> icons = new HashMap<>();


    public GameScreen(GameController gameController) {
        this.field = gameController.getField();
        imgInit(gameController);
    }

    private void imgInit(GameController gameController) {
        for (String name : gameController.getPrototypes().keySet()) {
            try (InputStream inputStream = getClass().getResourceAsStream("/animal_sprites/" + name + ".png")) {
                assert inputStream != null;
                BufferedImage read = ImageIO.read(inputStream);
                images.put(name, read);
                icons.put(name, new ImageIcon(images.get(name).getSubimage(0, 48, 48, 48)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        icons.put("Empty", new ImageIcon(images.get("Plant").getSubimage(48*5, 48*5, 48, 48)));
    }

    public Map<Cell, CButton> getButtons() {
        return buttons;
    }

    public Map<String, BufferedImage> getImages() {
        return images;
    }

    public Cell[][] getField() {
        return field;
    }

    public Map<String, ImageIcon> getIcons() {
        return icons;
    }
}
