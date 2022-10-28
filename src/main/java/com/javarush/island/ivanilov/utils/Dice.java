package com.javarush.island.ivanilov.utils;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public static double random() {
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0);
    }

    public static double random(double origin, double bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    public static int random(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static <E> E getRandomSetElement(Set<E> set) {
        int key = set.size();

        if (key == 0) {
            return null;
        }

        return set.stream().skip(new Random().nextInt(key)).findFirst().orElse(null);
    }
}
