package com.javarush.island.bogdan;

import com.javarush.island.bogdan.initializer.Initializer;
import com.javarush.island.bogdan.island.Island;
import com.javarush.island.bogdan.servise.Game;
import com.javarush.island.bogdan.servise.GameWorker;

public class Main {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        Island island = initializer.createIsland();
        island.showStatistic(island.getDay());
        Game game = new Game(island);
        GameWorker gameWorker = new GameWorker(game);
        gameWorker.start();
    }
}
