package com.javarush.island.karpiza.animals.predator;

import com.javarush.island.karpiza.service.Diet;


//Медведь
@Diet(eatAnaconda = 80, eatHorse = 40, eatDeer = 80, eatRabbit = 80, eatMouse = 90, eatGoat = 70, eatSheep = 70, eatBoar = 50, eatBuffalo = 20, eatDuck = 10)

public class Bear extends Predator {

    public static final double weight = 500;
    public static final int movingSpeed = 2;
    public static final double canEat = 80;


    private double hunger = canEat;

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public Bear(int xLocation, int yLocation) {
        super(xLocation, yLocation, movingSpeed);
    }
}