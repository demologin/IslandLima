package com.javarush.island.chebotareva.predator;

import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predator {
    private double weight = 6;
    private double minWeight = 3;
    private int maxAnimalAmount = 20; //заменить на 30
    private int speed = 3;
    private double kgFoodToBeFull = 1;
    Map<String, Integer> probabilitiesToEat = new HashMap<>();

    public void createTableOfProbabilities() {
        probabilitiesToEat.put("Wolf", 0);
        probabilitiesToEat.put("Snake", 0);
        probabilitiesToEat.put("Fox", 10);
        probabilitiesToEat.put("Bear", 0);
        probabilitiesToEat.put("Eagle", 0);
        probabilitiesToEat.put("Horse", 0);
        probabilitiesToEat.put("Deer", 0);
        probabilitiesToEat.put("Rabbit", 90);
        probabilitiesToEat.put("Mouse", 90);
        probabilitiesToEat.put("Goat", 0);
        probabilitiesToEat.put("Sheep", 0);
        probabilitiesToEat.put("Boar", 0);
        probabilitiesToEat.put("Buffalo", 0);
        probabilitiesToEat.put("Duck", 80);
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
