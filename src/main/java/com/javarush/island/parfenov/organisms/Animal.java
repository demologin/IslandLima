package com.javarush.island.parfenov.organisms;

import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public abstract class Animal extends Organism {
    public Animal(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }
}
