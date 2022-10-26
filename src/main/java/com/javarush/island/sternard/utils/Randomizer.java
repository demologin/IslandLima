package com.javarush.island.sternard.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Randomizer {
    public static int get(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
