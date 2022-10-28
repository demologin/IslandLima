package com.javarush.island.plugin;

import com.javarush.island.plugin.gameset.GameField;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        GameField gameField = new GameField();

        gameField.initialize();
        //gameField.print();
        System.out.println("*************");
        gameField.setLive();
        gameField.printInfo();

        while (true) {
            gameField.makeStep();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            gameField.printInfo();
        }

    }
}
