package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Sheep extends Herbivore implements Eatable {

    public Sheep(){
        maxAmountOfAnimal = 140;
        normalWeight = 70;
        weight = normalWeight;
        speed = 3;
        isHungry = false;
        mealAmountToEat = 15;
    }

    @Override
    public Sheep multiply() {
        return new Sheep();
    }

}
