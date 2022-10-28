package com.javarush.island.torkel.entity.animal.predator;

import com.javarush.island.torkel.entity.animal.Animal;
import com.javarush.island.torkel.entity.EntityType;

public abstract class Predator extends Animal {

    public Predator(EntityType type) {
        super(type);
    }
}
