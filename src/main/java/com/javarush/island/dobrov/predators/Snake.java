package com.javarush.island.dobrov.predators;


import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 15, maxAreaPopulation = 30,speed = 1,hunger = 3)

public class Snake extends Predator {
    @Override
    public String toString() {
        return "Snake";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.SNAKE;
    }
}
