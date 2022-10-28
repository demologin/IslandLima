package com.javarush.island.akhundov.plants;

import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.interfaces.Organism;

public abstract class Plant implements Eatable, Organism {
    protected double weight;
}
