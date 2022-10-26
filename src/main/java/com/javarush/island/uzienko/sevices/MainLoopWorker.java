package com.javarush.island.uzienko.sevices;

import com.javarush.island.uzienko.config.GameConfig;
import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.exceptions.IslandException;
import com.javarush.island.uzienko.view.GameView;
import com.javarush.island.uzienko.storage.Coords;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class MainLoopWorker extends Thread {
    private final GameView view;
    private final GameConfig config;
    private final GodsWillService godsWillService;
    private final StatisticService statisticService;

    @Override
    public void run() {
        updateUI();
        loop();
    }

    private void loop() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                iteration();
                if (statisticService.getSummary() == 0) {
                    executor.shutdown();
                }
            } catch (IslandException e) {
                throw new IslandException(e);
            }
        }, config.getMainLoopPeriod(), config.getMainLoopPeriod(), TimeUnit.MILLISECONDS);
    }

    private void iteration() {
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getCols(); j++) {
                Coords coords = new Coords(j, i);
                ExecutorService pool = Executors.newCachedThreadPool();
                Set<Resident> residentSet = new HashSet<>();
                Map<String, Set<Resident>> residentsMap = godsWillService.getResidents(coords);
                for (Set<Resident> value : residentsMap.values()) {
                    residentSet.addAll(value);
                }
                List<ResidentWorker> residentWorkers = residentSet.stream()
                        .map(entity -> new ResidentWorker(entity, godsWillService.whatIShouldToDo(entity)))
                        .toList();
                residentWorkers.forEach(pool::submit);
                pool.shutdown();
                try {
                    if (pool.awaitTermination(config.getMainLoopPeriod(), TimeUnit.MILLISECONDS)) {
                        updateUI();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void updateUI() {
        view.showMap();
        view.showStat();
    }
}
