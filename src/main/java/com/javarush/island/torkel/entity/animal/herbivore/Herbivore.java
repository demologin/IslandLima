package com.javarush.island.torkel.entity.animal.herbivore;

import com.javarush.island.torkel.entity.EntityType;
import com.javarush.island.torkel.entity.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(EntityType type){
        super(type);
    }
}
