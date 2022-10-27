package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.settings.GameSettings;

import static com.javarush.island.shubchynskyi.entity.EntityFactory.getFilledCell;

public class GameField {

    private final int width = GameSettings.GAME_FIELD_WIDTH;
    private final int height = GameSettings.GAME_FIELD_HEIGHT;
    private final Cell[][] gameField = new Cell[width][height];

    public GameField() {
        initialize();
    }

    public Cell[][] getGameField() {
        return gameField;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initialize() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gameField[x][y] = getFilledCell(x, y);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((y - 1) >= 0) gameField[x][y].addNeighbour(gameField[x][y - 1]);
                if ((x + 1) <= width - 1) gameField[x][y].addNeighbour(gameField[x + 1][y]);
                if ((y + 1) <= height - 1) gameField[x][y].addNeighbour(gameField[x][y + 1]);
                if ((x - 1) >= 0) gameField[x][y].addNeighbour(gameField[x - 1][y]);
            }
        }
    }
}