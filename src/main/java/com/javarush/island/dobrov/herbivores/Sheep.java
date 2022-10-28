package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 70, maxAreaPopulation = 140,speed = 3,hunger = 15)

public class Sheep extends Herbivore {
    @Override
    public String toString() {
        return "Sheep";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.SHEEP;
    }
}
