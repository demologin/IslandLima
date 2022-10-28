package com.javarush.island.parfenov.organisms.plants;

import com.javarush.island.parfenov.organisms.Organism;
import com.javarush.island.parfenov.organisms.Plants;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public class Plant extends Plants {

    public Plant(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }
}
