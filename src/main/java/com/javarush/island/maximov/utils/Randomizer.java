package com.javarush.island.maximov.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public static int randomIntFromToNotInclude(int min, int max) {
        if (min == max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
