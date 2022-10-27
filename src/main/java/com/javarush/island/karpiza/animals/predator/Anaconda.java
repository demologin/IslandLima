package com.javarush.island.karpiza.animals.predator;


import com.javarush.island.karpiza.service.Diet;

//Змея
@Diet(eatFox = 15, eatRabbit = 20, eatMouse = 40, eatDuck = 10)
public class Anaconda extends Predator {

    public static final double weight = 15;
    public static final int movingSpeed = 1;
    public static final double canEat = 3;


    private double hunger = canEat;

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public Anaconda(int xLocation, int yLocation) {
        super(xLocation, yLocation, movingSpeed);
    }
}