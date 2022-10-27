package com.javarush.island.marzhiievskyi.services;


import com.javarush.island.marzhiievskyi.entity.organisms.Organism;
import com.javarush.island.marzhiievskyi.services.factories.IslandFactory;
import com.javarush.island.marzhiievskyi.wiev.ConsoleGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadsWorker {
    private final int DELAY;
    private final int PERIOD;
    private final int cores;
    private final IslandFactory islandFactory;

    public ThreadsWorker() {
        islandFactory = new IslandFactory();
        DELAY = islandFactory.getParametersOfIsland().getDelayToStart();
        PERIOD = islandFactory.getParametersOfIsland().getPeriod();
        cores = Runtime.getRuntime().availableProcessors();
    }

    ConsoleGUI consoleGUI = new ConsoleGUI();

    public void startExecutor() {
        List<OrganismsWorker> workerList = new ArrayList<>();

        List<Organism> listOfPrototypes = islandFactory.getGameField().getListOfPrototypes();

        for (var organismType : listOfPrototypes) {
            workerList.add(new OrganismsWorker(islandFactory.getGameField(), organismType));
        }

        ScheduledExecutorService threadsPool = Executors.newScheduledThreadPool(cores);
        threadsPool.scheduleAtFixedRate(() -> {
            ExecutorService service = Executors.newFixedThreadPool(listOfPrototypes.size());
            workerList.forEach(service::execute);
            service.shutdown();
            try {
               if(service.awaitTermination(PERIOD, TimeUnit.SECONDS)) {
                   consoleGUI.printField(islandFactory.getGameField());
                   consoleGUI.printStatistic(islandFactory.getGameField());
               }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, DELAY, PERIOD, TimeUnit.SECONDS);


    }
}
