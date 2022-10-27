package com.javarush.island.torkel.entity.animal;

import com.javarush.island.torkel.entity.Entity;
import com.javarush.island.torkel.entity.EntityType;

import java.util.Random;

public abstract class Animal extends Entity {

    public Animal(EntityType type) {
        super(type);
    }

    public boolean eat(Entity entity) {
        double eatenAnimalWeight = entity.getCurrentWeight();
        double maxFood = this.getType().getMaxFood();
        double maxWeight = this.getType().getMaxWeight();
        double countOfEaten = this.getCountOfEaten();
        if ((countOfEaten + eatenAnimalWeight) < maxFood) {
            currentWeight += eatenAnimalWeight;
            this.setCountOfEaten(countOfEaten + eatenAnimalWeight);

        } else {
            currentWeight += maxFood - countOfEaten;
            this.setCountOfEaten(maxFood);
        }

        if (currentWeight > maxWeight) {
            currentWeight = maxWeight;
            return true;
        }
//        if (currentWeight < maxWeight) {
//            currentWeight = maxFood;
//            return true;
//        }
        if (this.getCountOfEaten() == maxFood) {
            return true;
        }
        return false;
    }

    @Override
    public Entity multiply() {
        int multiplyProbability = this.getType().getMultiplyProbability();
        int random = new Random().nextInt(100);
        if (this.getCurrentWeight() == this.getType().getMaxWeight()) {
            return Entity.createEntity(this.getType());
        }
        return null;
    }

    @Override
    public void changeWeight() {
        currentWeight -= currentWeight / 25.0;
    }


}
