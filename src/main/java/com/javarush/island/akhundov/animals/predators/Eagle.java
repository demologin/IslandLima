package com.javarush.island.akhundov.animals.predators;

public class Eagle extends Predator{

    public Eagle(){
        maxAmountOfAnimal = 20;
        normalWeight = 6;
        weight = normalWeight;
        speed = 3;
        isHungry = false;
        mealAmountToEat = 1;
    }

    @Override
    public Eagle multiply() {
        return new Eagle();
    }

}
