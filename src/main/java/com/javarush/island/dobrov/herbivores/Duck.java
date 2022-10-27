package com.javarush.island.dobrov.herbivores;



import com.javarush.island.dobrov.Grass.Grass;
import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;

@Animal_Properties(weight = 1, maxAreaPopulation = 200,speed = 4,hunger = 0.15)

public class Duck extends Herbivore {

    public void eat(Caterpillar caterpillar, Grass grass){

    }

    @Override
    public String toString() {
        return "Duck";
    }

    @Override
    public AnimalType returnType() {
        return AnimalType.DUCK;
    }
}
