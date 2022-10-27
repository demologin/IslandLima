package com.javarush.island.parfenov.organisms.animals;

import com.javarush.island.parfenov.organisms.Animal;
import com.javarush.island.parfenov.organisms.Herbivore;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public class Horse extends Animal implements Herbivore {

    public Horse(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }
}
