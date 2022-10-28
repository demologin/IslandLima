package com.javarush.island.chebotareva.herbivorous;

import java.util.HashMap;
import java.util.Map;

public class Horse extends Herbivorous {

    private double weight = 400;

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double minWeight = 200;
    private int maxAnimalAmount = 20; //заменить на 20
    private int speed = 4;
    private double kgFoodToBeFull = 60;
    Map<String, Integer> probabilitiesToEat = new HashMap<>();

    public void createTableOfProbabilities() {
        probabilitiesToEat.put("Wolf", 0);
        probabilitiesToEat.put("Snake", 0);
        probabilitiesToEat.put("Fox", 0);
        probabilitiesToEat.put("Bear", 0);
        probabilitiesToEat.put("Eagle", 0);
        probabilitiesToEat.put("Horse", 0);
        probabilitiesToEat.put("Deer", 0);
        probabilitiesToEat.put("Rabbit", 0);
        probabilitiesToEat.put("Mouse", 0);
        probabilitiesToEat.put("Goat", 0);
        probabilitiesToEat.put("Sheep", 0);
        probabilitiesToEat.put("Boar", 0);
        probabilitiesToEat.put("Buffalo", 0);
        probabilitiesToEat.put("Duck", 0);
        probabilitiesToEat.put("Caterpillar", 0);
        probabilitiesToEat.put("Plant", 100);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getMinWeight() {
        return minWeight;
    }

    @Override
    public int getMaxAnimalAmount() {
        return maxAnimalAmount;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public double getKgFoodToBeFull() {
        return kgFoodToBeFull;
    }
}
