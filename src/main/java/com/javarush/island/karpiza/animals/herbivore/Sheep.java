package com.javarush.island.karpiza.animals.herbivore;

import com.javarush.island.karpiza.service.Diet;

//Овца
@Diet(eatPlants = 100)

public class Sheep extends Herbivore {

    public static final double weight = 70;
    public static final int movingSpeed = 3;
    public static final double canEat = 15;

    public Sheep(int xLocation, int yLocation) {
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