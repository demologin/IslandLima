package com.javarush.island.sternard.organisms;

import com.javarush.island.sternard.organisms.parents.Carnivore;

import java.util.Map;

public class Eagle extends Carnivore {
    public Eagle(String name, double weight, int maxOnCell, String icon, String organismMainType, String organismType, Map<String, Integer> possibleFood, int speed, double energy, double maxFoodForSatiety) {
        super(name, weight, maxOnCell, icon, organismMainType, organismType, possibleFood, speed, energy, maxFoodForSatiety);
    }

}
