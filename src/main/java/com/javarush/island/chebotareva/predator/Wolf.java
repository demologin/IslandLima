package com.javarush.island.chebotareva.predator;

import com.javarush.island.chebotareva.data.Animal;

import java.util.HashMap;
import java.util.Map;


public class Wolf extends Animal {
    private double weight = 50;
    private double minWeight = 25;
    private int maxAnimalAmount = 30; //заменить на 30
    private int speed = 3;
    private double kgFoodToBeFull = 8;
    Map<String, Integer> probabilitiesToEat = new HashMap<>();

    public void createTableOfProbabilities() {
        probabilitiesToEat.put("Wolf", 0);
        probabilitiesToEat.put("Snake", 0);
        probabilitiesToEat.put("Fox", 0);
        probabilitiesToEat.put("Bear", 0);
        probabilitiesToEat.put("Eagle", 0);
        probabilitiesToEat.put("Horse", 10);
        probabilitiesToEat.put("Deer", 15);
        probabilitiesToEat.put("Rabbit", 60);
        probabilitiesToEat.put("Mouse", 80);
        probabilitiesToEat.put("Goat", 60);
        probabilitiesToEat.put("Sheep", 70);
        probabilitiesToEat.put("Boar", 15);
        probabilitiesToEat.put("Buffalo", 10);
        probabilitiesToEat.put("Duck", 40);
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
