package com.javarush.island.chebotareva.predator;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predator {
    private double weight = 500;
    private double minWeight = 250;
    private int maxAnimalAmount = 5; //заменить на 30
    private int speed = 2;
    private double kgFoodToBeFull = 80;
    Map<String, Integer> probabilitiesToEat = new HashMap<>();

    public void createTableOfProbabilities() {
        probabilitiesToEat.put("Wolf", 0);
        probabilitiesToEat.put("Snake", 80);
        probabilitiesToEat.put("Fox", 60);
        probabilitiesToEat.put("Bear", 0);
        probabilitiesToEat.put("Eagle", 0);
        probabilitiesToEat.put("Horse", 40);
        probabilitiesToEat.put("Deer", 80);
        probabilitiesToEat.put("Rabbit", 80);
        probabilitiesToEat.put("Mouse", 90);
        probabilitiesToEat.put("Goat", 70);
        probabilitiesToEat.put("Sheep", 70);
        probabilitiesToEat.put("Boar", 50);
        probabilitiesToEat.put("Buffalo", 20);
        probabilitiesToEat.put("Duck", 10);
        probabilitiesToEat.put("Caterpillar", 0);
        probabilitiesToEat.put("Plant", 0);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}

