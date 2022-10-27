package com.javarush.island.plugin.entity;

public enum Organism {
    PLANT,
    BEAR,
    EAGLE,
    FOX,
    SNAKE,
    WOLF,
    CATERPILLAR,
    COW,
    DEER,
    DUCK,
    MOUSE,
    HORSE,
    RABBIT,
    SHEEP;

    public static Organism[] getHerbivores() {
        Organism[] herbivores = {CATERPILLAR, COW, DEER, DUCK, MOUSE, HORSE, RABBIT, SHEEP};
        return herbivores;
    }
}
