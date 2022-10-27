package com.javarush.island.kolesnikova.entities.field;

import lombok.ToString;

@ToString
public class GameField {

    private static final int rowY = 20;
    private static final int colX = 100;

    private static final Cell[][] field = new Cell[rowY][colX];

    public static int getRowY() {
        return rowY;
    }

    public static int getColX() {
        return colX;
    }

    public static Cell[][] getField() {
        return field;
    }


}
