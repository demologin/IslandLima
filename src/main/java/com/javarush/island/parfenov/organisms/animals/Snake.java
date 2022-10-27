package com.javarush.island.parfenov.organisms.animals;

import com.javarush.island.parfenov.organisms.Animal;
import com.javarush.island.parfenov.organisms.Carnivore;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public class Snake extends Animal implements Carnivore {

    public Snake(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }
}
