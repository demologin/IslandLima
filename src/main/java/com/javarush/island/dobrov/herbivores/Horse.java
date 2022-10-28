package com.javarush.island.dobrov.herbivores;

import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 400, maxAreaPopulation = 20,speed = 4,hunger = 60)

public class Horse extends Herbivore {
    @Override
    public String toString() {
        return "Horse";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.HORSE;
    }
}
