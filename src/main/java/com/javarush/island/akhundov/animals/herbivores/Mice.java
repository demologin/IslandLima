package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.utility.Preferences;

public class Mice extends Herbivore implements Eatable {

    public Mice(){
        maxAmountOfAnimal = 500;
        normalWeight = 0.05;
        weight = normalWeight;
        speed = 1;
        isHungry = false;
        mealAmountToEat = 0.01;
    }

    @Override
    public Mice multiply() {
        return new Mice();
    }

    @Override
    public boolean eat(Eatable meal, Preferences preferences) {
        if(meal instanceof Grass){
            return true;
        }
        else {
            return tryToEat((Animal) meal, preferences);
        }
    }
}
