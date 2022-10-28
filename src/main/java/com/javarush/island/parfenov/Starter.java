package com.javarush.island.parfenov;

import com.javarush.island.parfenov.gameMechanics.GameController;
import com.javarush.island.parfenov.gameMechanics.PoolController;
import com.javarush.island.parfenov.settings.GameSettings;
import com.javarush.island.parfenov.statistics.Stat;
import com.javarush.island.parfenov.view.ViewInitializer;

public class Starter {

    public static void main(String[] args) {
        GameSettings gameSettings = new GameSettings();
        GameController gameController = new GameController(gameSettings);
        Stat stat = new Stat(gameController);
        ViewInitializer viewInitializer = new ViewInitializer(gameSettings, gameController, stat);
        PoolController poolController = new PoolController(gameController, viewInitializer, stat);
        poolController.begin();
    }
}
