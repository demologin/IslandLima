package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 700, maxAreaPopulation = 10,speed = 3,hunger = 100)

public class Buffalo extends Herbivore {
    @Override
    public String toString() {
        return "Buffalo";
    }
    @Override
    public AnimalType returnType() {
        return AnimalType.BUFFALO;
    }
}
