package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 2, maxAreaPopulation = 150,speed = 2,hunger = 0.45)

public class Bunny extends Herbivore {
    @Override
    public String toString() {
        return "Bunny";
    }
    @Override
    public AnimalType returnType() {
        return AnimalType.BUNNY;
    }
}
