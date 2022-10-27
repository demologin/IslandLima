package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 300, maxAreaPopulation = 20,speed = 4,hunger = 50)

public class Deer extends Herbivore {
    @Override
    public String toString() {
        return "Deer";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.DEER;
    }
}
