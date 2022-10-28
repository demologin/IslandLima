package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 0.05, maxAreaPopulation = 500,speed = 1,hunger = 0.01)

public class Mouse extends Herbivore {
    @Override
    public String toString() {
        return "Mouse";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.MOUSE;
    }
}
