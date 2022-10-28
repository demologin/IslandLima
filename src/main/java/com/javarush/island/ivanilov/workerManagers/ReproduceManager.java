package com.javarush.island.ivanilov.workerManagers;

import com.javarush.island.ivanilov.utils.Waiter;
import com.javarush.island.ivanilov.workers.Game;
import com.javarush.island.ivanilov.workers.ReproduceWorker;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class ReproduceManager extends Manager {

    public ReproduceManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = game.getGameField();
    }

    @Override
    public void run() {
        Arrays.stream(gameField.getRealm()).forEach(cells -> Arrays.stream(cells).forEach(cell -> {
            ReproduceWorker reproduceWorker = new ReproduceWorker(cell);
            executorService.submit(reproduceWorker);
        }));
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
    }
}
