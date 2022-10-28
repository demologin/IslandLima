package com.javarush.island.dobrov.services;

import com.javarush.island.dobrov.Animal;
import com.javarush.island.dobrov.Field.Cell;
import com.javarush.island.dobrov.Field.Field;
import com.javarush.island.dobrov.utils.Animal_Properties;


import java.util.*;

public class GameExecutor implements Runnable{

    private final Animal animal;

    public GameExecutor(Animal animal) {
        this.animal = animal;
    }



    public void run() {
        Cell[][] area = Field.getField();
        for (Cell[] cells : area) {
            for (Cell cell : cells) {
                animalsActions(cell, animal);
            }

        }

    }

    private void animalsActions(Cell cell, Animal animal) {
        Class<? extends Animal> aClass = animal.getClass();
        Animal_Properties annotation = aClass.getAnnotation(Animal_Properties.class);
        int speed = annotation.speed();
        double hunger = annotation.hunger();
        double weight = annotation.weight();

        Map<String, Set<Animal>> animals = cell.getAnimals();
        Set<Animal> animals1 = animals.get(aClass.getSimpleName());

        cell.getLock().lock();

        animals1.forEach(animal1 -> {
            animal1.move(cell, animal, speed);
            animal1.eat(cell, animal,hunger,weight);
            animal1.multiply(cell, animal);
        });

        cell.getLock().unlock();

    }
}
