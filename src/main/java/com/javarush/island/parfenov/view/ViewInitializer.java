package com.javarush.island.parfenov.view;

import com.javarush.island.parfenov.gameMechanics.GameController;
import com.javarush.island.parfenov.settings.GameSettings;
import com.javarush.island.parfenov.statistics.Stat;

public class ViewInitializer implements Runnable{
    GameSettings gameSettings;
    GameController gameController;
    Stat stat;
    public ViewInitializer(GameSettings gameSettings, GameController gameController, Stat stat) {
        this.gameSettings = gameSettings;
        this.gameController = gameController;
        this.stat = stat;
    }

    @Override
    public void run() {
        ViewController viewController = new ViewController(gameSettings, gameController, stat);
        viewController.gameLoop();
    }
}
