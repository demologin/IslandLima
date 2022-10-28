package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Deer extends Herbivore implements Eatable {

    public Deer(){
        maxAmountOfAnimal = 20;
        normalWeight = 300;
        weight = normalWeight;
        speed = 4;
        isHungry = false;
        mealAmountToEat = 50;
    }

    @Override
    public Deer multiply() {
        return new Deer();
    }

}
