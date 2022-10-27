package com.javarush.island.akhundov.animals.predators;

public class Wolf extends Predator{

    public Wolf(){
        maxAmountOfAnimal = 30;
        normalWeight = 50;
        weight = normalWeight;
        speed = 3;
        isHungry = false;
        mealAmountToEat = 8;
    }

    @Override
    public Wolf multiply() {
        return new Wolf();
    }

}
