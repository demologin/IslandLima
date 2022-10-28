package com.javarush.island.ivanilov.workerManagers;

import com.javarush.island.ivanilov.utils.Waiter;
import com.javarush.island.ivanilov.workers.EatWorker;
import com.javarush.island.ivanilov.workers.Game;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class EatManager extends Manager {


    public EatManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = game.getGameField();
    }

    @Override
    public void run() {
        Arrays.stream(gameField.getRealm())
                .forEach(cells -> Arrays.stream(cells)
                        .forEach(cell -> {
            EatWorker eatWorker = new EatWorker(cell);
            executorService.submit(eatWorker);
        }));
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
    }
}
