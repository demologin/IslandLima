package com.javarush.island.alimin.services;

import com.javarush.island.alimin.entity.location.Cell;

public class CellWorker implements Runnable {

    private final Cell cell;

    public CellWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
            cell.simulate();
    }
}
