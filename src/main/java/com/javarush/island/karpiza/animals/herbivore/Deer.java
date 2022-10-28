package com.javarush.island.karpiza.animals.herbivore;

import com.javarush.island.karpiza.service.Diet;

//Олень
@Diet(eatPlants = 100)

public class Deer extends Herbivore{

    public static final double weight = 300;
    public static final int movingSpeed = 4;
    public static final double canEat = 50;

    public Deer(int xLocation, int yLocation) {
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