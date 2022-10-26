package com.javarush.island.uzienko.entity.residents.carnivores;

import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.entity.residents.Animal;

public abstract class Carnivore extends Animal {

    public Carnivore(ResidentProperties properties) {
        super(properties);
    }
}
