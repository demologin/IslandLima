package com.javarush.island.bogdan.entity.animal.predator;

import com.javarush.island.bogdan.entity.EntityType;
import com.javarush.island.bogdan.entity.animal.Animal;

public abstract class Predator extends Animal {

    public Predator(EntityType type) {
        super(type);
    }
}