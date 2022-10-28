package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Caterpillar extends Herbivore implements Eatable {

    public Caterpillar(){
        maxAmountOfAnimal = 1000;
        normalWeight = 0.01;
        weight = normalWeight;
        speed = 0;
        isHungry = false;
        mealAmountToEat = 0;

    }

    @Override
    public Caterpillar multiply() {
        return  new Caterpillar();
    }

}
