package com.javarush.island.karpiza.animals.herbivore;

import com.javarush.island.karpiza.service.Diet;

//Буйвол
@Diet(eatPlants = 100)

public class Buffalo extends Herbivore {

    public static final double weight = 700;
    public static final int movingSpeed = 3;
    public static final double canEat = 100;

    public Buffalo(int xLocation, int yLocation) {
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