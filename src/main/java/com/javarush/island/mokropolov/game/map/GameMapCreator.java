package com.javarush.island.mokropolov.game.map;import com.javarush.island.mokropolov.Factory;public class GameMapCreator {    private final Factory entityFactory;    public GameMapCreator(Factory entityFactory) {        this.entityFactory = entityFactory;    }    public GameMap createRandomFilledGameMap(int rows, int cols) {        return createRandomFilledGameMap(rows, cols, false);    }    public GameMap createRandomFilledGameMap(int rows, int cols, boolean empty) {        GameMap gameMap = new GameMap(rows, cols);        Cell[][] cells = gameMap.getCells();        for (int row = 0; row < cells.length; row++) {            for (int col = 0; col < cells[row].length; col++) {                cells[row][col] = entityFactory.createRandomCell(empty);            }        }        for (int row = 0; row < cells.length; row++) {            for (int col = 0; col < cells[row].length; col++) {                cells[row][col].loadNextCell(gameMap, row, col);            }        }        return gameMap;    }}