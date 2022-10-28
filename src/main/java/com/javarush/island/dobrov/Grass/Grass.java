package com.javarush.island.dobrov.Grass;

import com.javarush.island.dobrov.Animal;
import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 1, maxAreaPopulation = 200)

public class Grass extends Animal {

    @Override
    public AnimalType returnType() {
        return AnimalType.Grass;
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
