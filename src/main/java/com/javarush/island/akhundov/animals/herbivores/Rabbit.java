package com.javarush.island.akhundov.animals.herbivores;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Rabbit extends Herbivore implements Eatable {

    public Rabbit(){
        maxAmountOfAnimal = 150;
        normalWeight = 2;
        weight = normalWeight;
        speed = 2;
        isHungry = false;
        mealAmountToEat = 0.45;
    }

    @Override
    public Rabbit multiply() {
        return new Rabbit();
    }

}
