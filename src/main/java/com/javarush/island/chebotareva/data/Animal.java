package com.javarush.island.chebotareva.data;

public abstract class Animal {
    private double weight;
    private double minWeight;
    private int maxAnimalAmount;
    private int speed;
    private double kgFoodToBeFull;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public int getMaxAnimalAmount() {
        return maxAnimalAmount;
    }

    public int getSpeed() {
        return speed;
    }

    public double getKgFoodToBeFull() {
        return kgFoodToBeFull;
    }

    public void move() {

    }

    public void reproduce() {

    }
}
