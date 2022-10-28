package com.javarush.island.bogdan.servise;

import com.javarush.island.bogdan.entity.EntityType;
import com.javarush.island.bogdan.island.Location;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWorker extends Thread {

    private final Game game;

    private static final long LIFE_CYCLE_DURATION = 1000;
    private static final Boolean STOP_ON_TIMEOUT = true;
    private static final int GAME_DURATION = 50000;

    public GameWorker(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.getIsland().showStatistic(game.getIsland().getDay());

        ScheduledExecutorService gameScheduledThreadPool = Executors.newScheduledThreadPool(4);
        gameScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitEntityWorkers, LIFE_CYCLE_DURATION, LIFE_CYCLE_DURATION, TimeUnit.MILLISECONDS);

        if (STOP_ON_TIMEOUT) runTimer();
    }

    private void runAndWaitEntityWorkers() {
        ArrayList<EntityWorker> entityWorkers = new ArrayList<>();

        for (int entityType = 0; entityType < EntityType.values().length; entityType++) {
            entityWorkers.add(new EntityWorker(entityType, game.getIsland()));
        }

        final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        for (EntityWorker entityWorker : entityWorkers) {
            fixedThreadPool.submit(entityWorker);
        }

        fixedThreadPool.shutdown();

        try {
            if (fixedThreadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                game.getIsland().plusDay();
                game.getIsland().showStatistic(game.getIsland().getDay());
                for (Location[] rows : game.getIsland().getLocations()) {
                    for (Location location : rows) {
                        clearingLocation(location);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("The game is finished");
        }
    }

    private void clearingLocation(Location location) {
        try {
            location.getLock().lock();
            location.clearingResetDayMapEntitiesToLocation();
        } finally {
            location.getLock().unlock();
        }
    }

    private void runTimer() {
        try {
            Thread.sleep(GAME_DURATION);
        } catch (InterruptedException e) {
            System.out.println("The game is finished");
        }
        System.out.println("The game is over by timeout");
        System.exit(1);
    }
}