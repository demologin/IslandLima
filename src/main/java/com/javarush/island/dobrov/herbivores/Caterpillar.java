package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 0.1, maxAreaPopulation = 1000)

public class Caterpillar extends Herbivore {
    @Override
    public String toString() {
        return "Caterpillar";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.CATERPILLAR;
    }
}
