package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.interfaces.Eatable;
import com.javarush.island.akhundov.plants.Grass;
import com.javarush.island.akhundov.utility.Preferences;

public class Duck extends Herbivore implements Eatable {

    public Duck(){
        maxAmountOfAnimal = 200;
        normalWeight = 1;
        weight = normalWeight;
        speed = 4;
        isHungry = false;
        mealAmountToEat = 0.15;
    }

    @Override
    public Duck multiply() {
      return new Duck();
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
