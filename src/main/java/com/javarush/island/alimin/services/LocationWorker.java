package com.javarush.island.alimin.services;

import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.entity.location.Location;
import com.javarush.island.alimin.exception.IslandException;
import com.javarush.island.alimin.properties.Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocationWorker extends Thread {
    private final Location location;
    private final int processorCount = Runtime.getRuntime().availableProcessors();

    public LocationWorker() {
        this.location = Location.getInstance();
    }
/*
для вывода подробной статистики по каждой ячейке можно раскомментировать вызов метода
printLocationState(), решился его закомментировать, когда модифицировал метод printStatistics(),
но не решился удалять, на случай, если вдруг нужно наблюдать за перемещением животных
 */
    @Override
    public void run() {
//        location.printLocationState();
        location.printStatistics();
        Cell[][] cells = location.getLocation();
        List<CellWorker> cellWorkers = new ArrayList<>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(processorCount);
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                CellWorker cellWorker = new CellWorker(cell);
                cellWorkers.add(cellWorker);
            }
        }
        executorService.scheduleAtFixedRate(() -> {
            ExecutorService service = Executors.newCachedThreadPool();
            cellWorkers.forEach(service::submit);
            service.shutdown();
            try {
                if (service.awaitTermination(1, TimeUnit.DAYS)) {
//                    location.printLocationState();
                    int size = location.printStatistics();
                    if (size <= 0) {
                        executorService.shutdown();
                        System.out.println("Настал конец времён!");
                    }
                }
            } catch (InterruptedException e) {
                throw new IslandException(e);
            }
        }, Properties.PERIOD, Properties.PERIOD, TimeUnit.MILLISECONDS);
    }
}
