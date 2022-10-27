package com.javarush.island.chebotareva.thread;

import com.javarush.island.chebotareva.field.Cell;

public class ThreadCreation implements Runnable {

    private final Cell cell;
    public ThreadCreation(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        try {
            cell.eatInCell();
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            System.out.println("ошибка в run " + ex);
        }
    }
}


