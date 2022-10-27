package com.javarush.island.chebotareva.herbivorous;

import java.util.HashMap;
import java.util.Map;

public class Rabbit extends Herbivorous {
    private double weight = 2;
    private double minWeight = 1;
    private int maxAnimalAmount = 150;
    private int speed = 2;
    private double kgFoodToBeFull = 0.45;
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
