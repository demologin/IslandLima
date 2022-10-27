package com.javarush.island.karpiza.animals.herbivore;

import com.javarush.island.karpiza.service.Diet;


//Гусеница
@Diet(eatPlants = 100)

public class Caterpillar extends Herbivore{

    public static final double weight = 0.01;
    public static final int movingSpeed = 0;
    public static final double canEat = 0.01;

    public Caterpillar(int xLocation, int yLocation) {
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