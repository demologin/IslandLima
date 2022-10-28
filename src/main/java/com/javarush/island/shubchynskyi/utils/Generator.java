package com.javarush.island.shubchynskyi.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private Generator() {
    }

    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int getRandomForSpawn(int min, int max) {
        if (min == 0 && max == 1) {
            return getRandom(min, (max + 1));
        } else return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static boolean checkChance(int percent) {
        return getRandom(0, 100) < percent;
    }

}
