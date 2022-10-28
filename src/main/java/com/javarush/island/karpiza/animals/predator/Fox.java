package com.javarush.island.karpiza.animals.predator;


import com.javarush.island.karpiza.service.Diet;

//Лиса
@Diet(eatRabbit = 70, eatMouse = 90, eatDuck = 60, eatCaterpillar = 40)

public class Fox extends Predator {

    public static final double weight = 8;
    public static final int movingSpeed = 2;
    public static final double canEat = 2;

    private double hunger = canEat;

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public Fox(int xLocation, int yLocation) {
        super(xLocation, yLocation, movingSpeed);
    }
}