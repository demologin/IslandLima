package com.javarush.island.akhundov.animals.predators;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Boa extends Predator implements Eatable {

    public Boa(){
        maxAmountOfAnimal = 30;
        normalWeight = 15;
        weight = normalWeight;
        speed = 1;
        isHungry = false;
        mealAmountToEat = 3;
    }

    @Override
    public Boa multiply() {
        return new Boa();
    }

}
