package com.javarush.island.shubchynskyi;

import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.services.GameWorker;
import com.javarush.island.shubchynskyi.view.ConsoleView;
import com.javarush.island.shubchynskyi.view.View;

public class Runner {
    public static void main(String[] args) {
        GameField gamefield = new GameField();
        View viewer = new ConsoleView(gamefield);
        GameWorker gameWorker = new GameWorker(gamefield, viewer);
        gameWorker.run();

    }
}
