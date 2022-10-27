package com.javarush.island.akhundov.animals.predators;

import com.javarush.island.akhundov.interfaces.Eatable;

public class Fox extends Predator implements Eatable {

    public Fox(){
        maxAmountOfAnimal = 30;
        normalWeight = 8;
        weight = normalWeight;
        speed = 2;
        isHungry = false;
        mealAmountToEat = 2;
    }

    @Override
    public Fox multiply() {
        return new Fox();
    }

}
