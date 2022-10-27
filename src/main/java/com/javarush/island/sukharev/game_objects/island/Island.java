package com.javarush.island.sukharev.game_objects.island;

import java.util.Arrays;

public class Island {
    private static Island island;
    public Cell[][] matrix;

    private Island(Cell[][] matrix) {
        this.matrix = matrix;

    }

    public static synchronized Island creationIsland(Cell[][] matrix) {
        if (island == null) {
            island = new Island(matrix);
        }
        return island;
    }

    public static Island getIsland() {
        return island;
    }

    public void look() {
        for (Cell[] cells : matrix) {
            for (Cell cell : cells) {
                System.out.print(cell);
            }
            System.out.println();
        }

    }

    public Cell findCage(int x, int y) {
        return matrix[x][y];
    }


    @Override
    public String toString() {
        return "Island{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
