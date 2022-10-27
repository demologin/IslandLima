package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.utility.Preferences;

public class Boar extends Herbivore implements Eatable {


    public Boar(){
        maxAmountOfAnimal = 50;
        normalWeight = 400;
        weight = normalWeight;
        speed = 2;
        isHungry = false;
        mealAmountToEat = 50;
    }


    @Override
    public Boar multiply() {
        return new Boar();
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

    @Override
    public String toString() {
        return super.toString();
    }
}
