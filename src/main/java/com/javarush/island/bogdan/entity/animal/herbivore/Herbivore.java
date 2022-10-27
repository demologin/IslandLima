package com.javarush.island.bogdan.entity.animal.herbivore;

import com.javarush.island.bogdan.entity.EntityType;
import com.javarush.island.bogdan.entity.animal.Animal;

public abstract class Herbivore extends Animal {
    protected Herbivore(EntityType type) {
        super(type);
    }
}
