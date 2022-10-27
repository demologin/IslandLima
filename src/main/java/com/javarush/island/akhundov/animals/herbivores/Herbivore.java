package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.utility.Preferences;

public abstract class Herbivore extends Animal implements Eatable {
    @Override
    public boolean eat(Eatable meal, Preferences preferences){
        return meal instanceof Grass;
    }
}
