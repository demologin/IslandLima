package com.javarush.island.parfenov.organisms.animals;

import com.javarush.island.parfenov.organisms.Animal;
import com.javarush.island.parfenov.organisms.Carnivore;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public class Fox extends Animal implements Carnivore {

    public Fox(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }
}
