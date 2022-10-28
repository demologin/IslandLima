package com.javarush.island.dobrov.herbivores;

import com.javarush.island.dobrov.Animal;
import com.javarush.island.dobrov.Field.Cell;
import com.javarush.island.dobrov.Grass.Grass;
import com.javarush.island.dobrov.utils.RandomGenerator;
import com.javarush.island.dobrov.utils.Settings;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Herbivore extends Animal {

    @Override
    public void eat(Cell cell, Animal animal, double hunger, double weight) {
        cell.getLock().lock();
        int generate = new RandomGenerator(100).generate();
        for (Map.Entry<String, Set<Animal>> s : cell.getAnimals().entrySet()) {

            Iterator<Animal> iterator = s.getValue().iterator();
            while (iterator.hasNext()) {
                Animal next = iterator.next();
                if (next instanceof Grass) {
                    int prob = Settings.probabilities[animal.returnType().ordinal()][next.returnType().ordinal()];
                    if (generate < prob)
                        iterator.remove();
                }
            }

        }
        cell.getLock().unlock();
    }

    @Override
    public void move(Cell cell, Animal animal1, int speed) {
        super.move(cell, animal1, speed);
    }

    @Override
    public void multiply(Cell cell, Animal animal) {
        super.multiply(cell, animal);
    }

    @Override
    public void dead() {
        super.dead();
    }
}
