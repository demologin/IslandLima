package com.javarush.island.ivanilov.workers;

import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.game.Cell;

import java.util.Set;


public record TransferWorker(Cell departure, Cell arrival, Animal animal) implements Runnable {

    @Override
    public void run() {
        arrival.getLock().lock();
        departure.getLock().lock();
        Set<Animal> departureSet = departure.getAnimals().get(animal.getClass());
        departureSet.remove(animal);
        Set<Animal> arrivalSet = arrival.getAnimals().get(animal.getClass());
        arrivalSet.add(animal);
        departure.getLock().unlock();
        arrival.getLock().unlock();
    }
}
