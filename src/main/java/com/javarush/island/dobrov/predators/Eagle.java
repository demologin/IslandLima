package com.javarush.island.dobrov.predators;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 6, maxAreaPopulation = 20,speed = 3,hunger = 1)

public class Eagle extends Predator {
    @Override
    public String toString() {
        return "Eagle";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.EAGLE;
    }
}
