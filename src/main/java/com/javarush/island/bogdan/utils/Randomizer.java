package com.javarush.island.bogdan.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public static int getRandom(int to) {
        return ThreadLocalRandom.current().nextInt(to);
    }

    public static int getRandom(int from, int to) {
        if (to <= 0) {
            to = 0;
        }
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static boolean getRandomBoolean(int chance) {
        return chance >= getRandom(100);
    }
}

