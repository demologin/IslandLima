package com.javarush.island.torkel.entity;

import com.javarush.island.torkel.entity.animal.herbivore.*;
import com.javarush.island.torkel.entity.animal.predator.*;
import com.javarush.island.torkel.entity.plant.*;

import java.util.Random;

public abstract class Entity {
    private final EntityType type;

    private double countOfEaten = 0;

    protected double currentWeight;

    public abstract Entity multiply();

    public abstract void changeWeight();

    public double getCountOfEaten() {
        return countOfEaten;
    }

    public void setCountOfEaten(double countOfEaten) {
        this.countOfEaten = countOfEaten;
    }

    public boolean isDie() {
        return currentWeight < type.getMaxWeight() / 2;
    }

    public static Entity createEntity(EntityType type) {
        return switch (type) {
            case BEAR -> new Bear(type);
            case BOA -> new Boa(type);
            case EAGLE -> new Eagle(type);
            case FOX -> new Fox(type);
            case WOLF -> new Wolf(type);
            case BOAR -> new Boar(type);
            case BUFFALO -> new Buffalo(type);
            case CATERPILLAR -> new Caterpillar(type);
            case DEER -> new Deer(type);
            case DUCK -> new Duck(type);
            case GOAT -> new Goat(type);
            case HORSE -> new Horse(type);
            case MOUSE -> new Mouse(type);
            case RABBIT -> new Rabbit(type);
            case SHEEP -> new Sheep(type);
            case PLANT -> new Plant(type);
//            default -> new Plant(type);
        };
    }

    public EntityType getType() {
        return type;
    }

    public Entity(EntityType type) {
        this.type = type;
        currentWeight = type.getMaxWeight() / 100 * 70;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }
}
