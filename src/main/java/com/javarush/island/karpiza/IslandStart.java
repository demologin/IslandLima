package com.javarush.island.karpiza;

import com.javarush.island.karpiza.island.Island;
import com.javarush.island.karpiza.island.Location;
import com.javarush.island.karpiza.service.PlantGrowther;
import com.javarush.island.karpiza.service.Statistics;
import com.javarush.island.karpiza.service.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IslandStart {

    private static ScheduledExecutorService executorTasks;
    private static ScheduledExecutorService executorPlantGrowth;
    private static ScheduledExecutorService executorStatistics;

    public static void main(String[] args) {
        Island island = new Island();
        island.initialize();
        Statistics stat = new Statistics(island);
        island.print();

        executorTasks = Executors.newScheduledThreadPool(8);
        executorPlantGrowth = Executors.newScheduledThreadPool(1);
        executorStatistics = Executors.newScheduledThreadPool(1);


        for (int i = 0; i < island.getLocations().length; i++) {
            for (int j = 0; j < island.getLocations()[i].length; j++) {
                Location location = island.getLocations()[i][j];
                executorTasks.scheduleWithFixedDelay(new Task(location, island), 1, 1, TimeUnit.SECONDS);
                executorPlantGrowth.scheduleWithFixedDelay(new PlantGrowther(location), 2, 3, TimeUnit.SECONDS);
            }
        }
        executorStatistics.scheduleWithFixedDelay(stat, 500, 1000, TimeUnit.MILLISECONDS);
    }

    public static void stop() {
        executorTasks.shutdown();
        executorPlantGrowth.shutdown();
        executorStatistics.shutdown();
        System.out.println();
        System.out.println("All animals died...");
    }
}