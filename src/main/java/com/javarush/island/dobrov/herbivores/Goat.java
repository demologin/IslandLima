package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 60, maxAreaPopulation = 140,speed = 3,hunger = 10)

public class Goat extends Herbivore {
    @Override
    public String toString() {
        return "Goat";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.GOAT;
    }
}
