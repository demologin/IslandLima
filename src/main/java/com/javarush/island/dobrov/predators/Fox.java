package com.javarush.island.dobrov.predators;

import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 8, maxAreaPopulation = 30,speed = 2,hunger = 2)

public class Fox extends Predator {
    @Override
    public String toString() {
        return "Fox";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.FOX;
    }
}
