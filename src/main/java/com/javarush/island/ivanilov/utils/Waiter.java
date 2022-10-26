package com.javarush.island.ivanilov.utils;

import com.javarush.island.ivanilov.exceptions.IslandGameException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Waiter {
    public static void awaitTermination(ExecutorService executorService) {
        try {
            executorService.shutdown();
            while (!executorService.awaitTermination(50, TimeUnit.MILLISECONDS)) {
                Sleeper.sleep(100);
            }
        } catch (Exception e) {
            throw new IslandGameException(e);
        }
    }
}
