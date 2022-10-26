package com.javarush.island.sternard.organisms.parents;

import java.util.Map;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, double weight, int maxOnCell, String icon, String organismMainType, String organismType, Map<String, Integer> possibleFood, int speed, double energy, double maxFoodForSatiety) {
        super(name, weight, maxOnCell, icon, organismMainType, organismType, possibleFood, speed, energy, maxFoodForSatiety);
    }
}
