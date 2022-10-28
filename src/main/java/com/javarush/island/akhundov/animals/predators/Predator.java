package com.javarush.island.akhundov.animals.predators;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.utility.Preferences;

public abstract class Predator extends Animal {
    @Override
    public boolean eat(Eatable meal, Preferences preferences){
        return tryToEat((Animal) meal,preferences);
    }
}
