package com.javarush.island.ivanilov.workers;

import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.creatures.Plant;
import com.javarush.island.ivanilov.game.Cell;
import com.javarush.island.ivanilov.game.Settings;
import com.javarush.island.ivanilov.utils.Dice;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReproduceWorker implements Runnable {
    private final Cell cell;

    public ReproduceWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        animalBirth();
        reproducePlants();
        reproduceAnimals();
    }

    private void animalBirth() {
        cell.getAnimals()
                .forEach((type, animals) -> pregnancy(animals));
    }

    private void pregnancy(Set<Animal> animals) {
        if (animals.size() == 0)
            return;

        if (animals.size() >= animals.stream().findFirst().get().getAnimalLimits().getMaxPopulationInCell())
            return;

        Set<Animal> pregnantAnimals = animals.stream()
                .filter(Animal::isPregnant)
                .collect(Collectors.toSet());

        if (pregnantAnimals.size() == 0)
            return;

        pregnantAnimals.forEach(animal -> {
                    if (animal.getPregnancyCounter().decrementAndGet() == 0) {
                        animal.setPregnant(false);
                        int children =
                                Dice.random(animal.getBreedingParams().getMinOffsprings(), animal.getBreedingParams().getMaxOffsprings());

                        while (children > 0) {
                            Animal child = animal.clone();
                            animals.add(child);
                            children--;
                        }
                    }
                });
    }

    private void reproduceAnimals() {
        Set<Map.Entry<Type, Set<Animal>>> entries = cell.getAnimals().entrySet();
        Set<Map.Entry<Type, Set<Animal>>> entriesToBeReproduced = entries.stream()
                .filter(entry -> {
                    Animal prototype = (Animal) cell.getGameField().getAnimalBuilder().prototypes.get(entry.getKey());
                    return (entry.getValue().size() < prototype.getAnimalLimits().getMaxPopulationInCell()) && (entry.getValue().size() > 0);
                }).collect(Collectors.toSet());

        for (var entry: entriesToBeReproduced) {
            Set<Animal> animals = entry.getValue();
            Set<Animal> females = animals.stream().filter(Animal::isFemale).collect(Collectors.toSet());
            Set<Animal> males = animals.stream().filter(animal -> !animal.isFemale()).collect(Collectors.toSet());

            if (females.size() != 0) {
                for (var male : males) {
                    Animal target = findTarget(females);
                    male.getLock().lock();
                    target.getLock().lock();
                    male.reproduce(target);
                    target.getLock().unlock();
                    male.getLock().unlock();
                }
            }
        }
    }

    private void reproducePlants() {
        Plant plants = cell.getPlants();

        if (plants.getMass() == 0) {
            double chanceOfNewPopulation = Dice.random();
            if (chanceOfNewPopulation > Settings.settings.MIN_CHANCE_OF_NEW_POPULATION) {
                int newPopulationLimit = Plant.maxMassInCell / 100;
                plants.setMass(Dice.random(0, newPopulationLimit));
            }
            return;
        }

        if (plants.getMass() < Plant.maxMassInCell) {
            plants.reproduce(plants);
        }
    }

    private Animal findTarget(Set<Animal> females) {
        return Dice.getRandomSetElement(females);
    }

}
