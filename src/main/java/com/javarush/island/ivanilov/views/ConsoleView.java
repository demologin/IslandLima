package com.javarush.island.ivanilov.views;

import com.javarush.island.ivanilov.entities.GameInfo;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.game.GameField;
import com.javarush.island.ivanilov.utils.Sleeper;
import com.javarush.island.ivanilov.workers.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ConsoleView implements View {
    public static final String LEGEND = "Legend for each cell: the most popular creature \\ plants. Sign \"-\" means no com.javarush.ivanilov.creatures left in the cell \n" +
            "More details are provided for a field with 10 or less columns.";
    public static final String GAME_OVER = "All animals are dead. \n" +
            "Game over.";
    public static final String NO_GAME_STATS = "No com.javarush.ivanilov.game stats available";

    Game game;

    @Override
    public void runGame() {
        Thread thread = new Thread(game);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }

        while (!game.isStopped()) {
            GameInfo gameInfo = game.doIteration();
            printSummary(gameInfo);
            System.out.println(LEGEND);
            printField(game.getGameField());
            System.out.println();
            Sleeper.sleep();
        }
        System.out.println(GAME_OVER);
    }

    private void printField(GameField gameField) {
        System.out.println(gameField.printField());
    }

    private void printSummary(GameInfo gameInfo) {
        if (gameInfo == null) {
            throw new IslandGameException(NO_GAME_STATS);
        } else {
            System.out.println(gameInfo.getStats());
        }
    }
}
