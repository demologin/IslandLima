package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.statistics.Stat;
import com.javarush.island.parfenov.view.ViewInitializer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolController {
    public static final int TIMEOUT = 100;
    private final GameController gameController;
    private final ViewInitializer viewInitializer;

    private final Stat stat;

    public PoolController(GameController gameController, ViewInitializer viewInitializer, Stat stat) {
        this.gameController = gameController;
        this.viewInitializer = viewInitializer;
        this.stat = stat;
    }

    public void begin() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);//1 + gameController.getPrototypes().size());
        executorService.execute(viewInitializer);
        List<OrganismController> workers = gameController
                .getPrototypes()
                .keySet()
                .stream()
                .map(o -> new OrganismController(gameController, o, stat))
                .toList();
        executorService.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newWorkStealingPool();
            workers.forEach(servicePool::submit);
            servicePool.shutdown();
            try {
                servicePool.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, TIMEOUT, TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
