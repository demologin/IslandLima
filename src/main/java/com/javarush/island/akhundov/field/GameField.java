package com.javarush.island.akhundov.field;

public class GameField {
    public Cell[][] field;
    private final int height;
    private final int width;

    public GameField(int height, int width){
        this.height = height;
        this.width = width;
        field = new Cell[height][width];
    }

    public Cell getCell(int height, int width){
        return field[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
