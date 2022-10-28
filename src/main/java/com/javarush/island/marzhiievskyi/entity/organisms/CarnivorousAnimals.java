package com.javarush.island.marzhiievskyi.entity.organisms;

public abstract class CarnivorousAnimals extends Animals {

    public CarnivorousAnimals(String name, String icon, double weight, int maxCountOnCell, int maxSpeed, double needFood) {
        super(name, icon, weight, maxCountOnCell, maxSpeed, needFood);
    }

}
