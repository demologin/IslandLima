package com.javarush.island.parfenov.view;

import com.javarush.island.parfenov.gameMechanics.Cell;
import com.javarush.island.parfenov.gameMechanics.GameController;
import com.javarush.island.parfenov.gameMechanics.Vault;
import com.javarush.island.parfenov.settings.GameSettings;
import com.javarush.island.parfenov.statistics.Stat;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ViewController extends JFrame {

    public static final Color BACKGROUND_COLOR = new Color(48, 80, 55);
    public static final Color BORDER_COLOR = new Color(80, 48, 73);
    private final GameController gameController;
    private final GameScreen gameScreen;
    private final RightPanel rightPanel;
    private final double timePerFrame;
    private long lastFrame;
    private final int height;
    private final int width;
    private final Stat stat;
    private final int CELL_SIZE = 48;
    public ViewController(GameSettings gameSettings, GameController gameController, Stat stat) {
        this.gameController = gameController;
        this.stat = stat;
        this.height = gameSettings.getRealFieldHeight();
        this.width = gameSettings.getRealFieldWidth();
        int widthOfScreen = gameSettings.getRealFieldWidth() * CELL_SIZE;
        int heightOfScreen = gameSettings.getRealFieldHeight() * CELL_SIZE;
        setSize(widthOfScreen + 200, heightOfScreen + 200);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(gameController);
        timePerFrame = 1_000_000_000.0 / 10.0;
        rightPanel = new RightPanel(gameSettings);
        System.out.println(gameSettings.getRealFieldHeight());
        JPanel panel = new JPanel(new GridLayout(gameSettings.getRealFieldHeight(), gameSettings.getRealFieldWidth()));
        makeButtons(panel);
        add(panel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        pack();
        setVisible(true);

    }

    private void makeButtons(JPanel panel) {
        Map<Cell, CButton> buttons = gameScreen.getButtons();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                CButton valueButton = new CButton(gameScreen);
                Cell key = gameController.getField()[y][x];
                buttons.put(key, valueButton);
                valueButton.setBackground(BACKGROUND_COLOR);
                valueButton.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
                panel.add(valueButton);
            }
        }
    }

    public void gameLoop() {
        Cell[][] field = gameScreen.getField();
        Map<Cell, CButton> buttons = gameScreen.getButtons();
        Map<String, ImageIcon> icons = gameScreen.getIcons();
        ImageIcon emptyImage = icons.get("Empty");

        while(true) {
            for (Cell[] cells : field) {
                for (Cell cell : cells) {
                    String groupName = getBiggestGroup(cell.getPersons());
                    if (!groupName.isEmpty()) {
                        buttons.get(cell).changeImage(icons.get(groupName));
                    } else {
                        buttons.get(cell).changeImage(emptyImage);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : stat.getAmountOfOrganisms().entrySet()) {
                JLabel label = rightPanel.getNameToLabel().get(entry.getKey());
                label.setText(rightPanel.getNameToUnicode().get(entry.getKey()) + entry.getValue());
            }
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
            }
        }
    }

    private String getBiggestGroup(Map<String, Vault> persons) {
        String result = "";
        int amount = 0;
        for (Map.Entry<String, Vault> entry : persons.entrySet()) {
            int sizeOfGroup = entry.getValue().getAmountOfResidents();
            if (sizeOfGroup > amount) {
                amount = sizeOfGroup;
                result = entry.getKey();
            }
        }
        return result;
    }
}
