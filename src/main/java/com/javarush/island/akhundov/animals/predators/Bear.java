package com.javarush.island.akhundov.animals.predators;

public class Bear extends Predator{

    public Bear(){
        maxAmountOfAnimal = 5;
        normalWeight = 500;
        weight = normalWeight;
        speed = 2;
        isHungry = false;
        mealAmountToEat = 80;
    }

    @Override
    public Bear multiply() {
        return new Bear();
    }

}
