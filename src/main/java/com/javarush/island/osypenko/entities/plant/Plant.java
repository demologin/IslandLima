package com.javarush.island.osypenko.entities.plant;

import com.javarush.island.osypenko.entities.Organism;
import com.javarush.island.osypenko.pref.ObjectPrefs;

public abstract class Plant extends Organism {
    public Plant(ObjectPrefs objectPrefs) {
        super(objectPrefs);
    }
}
