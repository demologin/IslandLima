package com.javarush.island.dobrov.herbivores;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 400, maxAreaPopulation = 50,speed = 2,hunger = 50)

public class Hog extends Herbivore {
    @Override
    public String toString() {
        return "Hog";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.HOG;
    }
}
