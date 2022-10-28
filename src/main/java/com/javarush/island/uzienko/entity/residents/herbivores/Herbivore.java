package com.javarush.island.uzienko.entity.residents.herbivores;

import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.entity.residents.Animal;

public abstract class Herbivore extends Animal {

    public Herbivore(ResidentProperties properties) {
        super(properties);
    }
}
