package com.javarush.island.dobrov.predators;

import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 50, maxAreaPopulation = 30,speed = 3,hunger = 8)

public class Wolf extends Predator{


    @Override
    public String toString() {
        return "Wolf";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.WOLF;
    }
}
