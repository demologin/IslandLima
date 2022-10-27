package com.javarush.island.osypenko.entities.animal;

import com.javarush.island.osypenko.entities.Organism;
import com.javarush.island.osypenko.pref.ObjectPrefs;

public abstract class Animal extends Organism {
    public Animal(ObjectPrefs objectPrefs) {
        super(objectPrefs);
    }
}

