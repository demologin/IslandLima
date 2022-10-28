package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Horse extends Herbivore implements Eatable {

    public Horse(){
        normalWeight = 400;
        weight = normalWeight;
        speed = 4;
        isHungry = false;
        mealAmountToEat = 60;
        maxAmountOfAnimal = 20;
    }

    @Override
    public Horse multiply() {
        return new Horse();
    }

}
