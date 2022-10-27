package com.javarush.island.dobrov.predators;

import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 500, maxAreaPopulation = 5,speed = 2,hunger = 80)

public class Bear extends Predator
{
    @Override
    public String toString() {
        return "Bear";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.BEAR;
    }

    String s ="U+D83D";

}
