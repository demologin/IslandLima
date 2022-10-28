package com.javarush.island.akhundov.plants;

public class Grass extends Plant{

    public Grass(){
        weight = 1;
    }

    public int getMaxAmountOfPlant() {
        return 200;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
