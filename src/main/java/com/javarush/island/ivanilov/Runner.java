package com.javarush.island.ivanilov;

import com.javarush.island.ivanilov.game.Settings;
import com.javarush.island.ivanilov.views.ConsoleView;
import com.javarush.island.ivanilov.workers.Game;

public class  Runner {

    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.parseSettings();
        Game game = new Game(Settings.settings.ROWS, Settings.settings.COLUMNS);
        ConsoleView consoleView = new ConsoleView(game);
        consoleView.runGame();
    }
}