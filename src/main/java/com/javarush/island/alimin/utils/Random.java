package com.javarush.island.alimin.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    private Random() {
    }
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public static boolean getProbability(int probabilityPercent) {
        return ThreadLocalRandom.current().nextInt(0, 100) < probabilityPercent;
    }
}
