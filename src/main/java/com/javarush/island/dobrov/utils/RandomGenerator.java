package com.javarush.island.dobrov.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
private final int number;

    public RandomGenerator(int number) {
        this.number = number;
    }

    public int generate(){

        return ThreadLocalRandom.current().nextInt(0, number);
    }
}
