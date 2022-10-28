package com.javarush.island.karpiza.animals.herbivore;

import com.javarush.island.karpiza.service.Diet;

//Лошадь
@Diet(eatPlants = 100)

public class Horse extends Herbivore{

    public static final double weight = 400;
    public static final int movingSpeed = 4;
    public static final double canEat = 60;

    public Horse(int xLocation, int yLocation) {
        super(xLocation, yLocation, movingSpeed);
    }
    private double hunger = canEat;

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }
}