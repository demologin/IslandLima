package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;

import java.util.List;

public class GameMapCreator {
    private final Factory entityFactory;

    public GameMapCreator(Factory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public GameMap createRandomFilledGameMap(int rows, int cols) {
        GameMap gameMap = new GameMap(rows, cols);
        Cell[][] cells = gameMap.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col] = entityFactory.createRandomCell();
            }
        }
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                List<Cell> nextCell = cell.getNextCell();
                if (row > 0) nextCell.add(cells[row - 1][col]);
                if (col > 0) nextCell.add(cells[row][col - 1]);
                if (row < rows - 1) nextCell.add(cells[row + 1][col]);
                if (col < cols - 1) nextCell.add(cells[row][col + 1]);
            }
        }
        return gameMap;
    }
}
