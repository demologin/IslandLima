package com.javarush.island.ivanilov.builders;

import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.creatures.Plant;
import com.javarush.island.ivanilov.entities.Terrain;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.game.Cell;
import com.javarush.island.ivanilov.game.GameField;
import com.javarush.island.ivanilov.game.Settings;
import com.javarush.island.ivanilov.utils.Dice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class CellBuilder implements Callable<Cell> {
    private final GameField gameField;
    private final Map<Type, Object> prototypes;

    public CellBuilder(GameField gameField, AnimalBuilder animalBuilder) {
        this.gameField = gameField;
        prototypes = animalBuilder.getPrototypes();
    }

    @Override
    public Cell call() {
        return generateRandomlyFilledCell();
    }

    public Cell generateRandomlyFilledCell() {
        Map<Type, Set<Animal>> animals = new HashMap<>();

        for (var entry : prototypes.entrySet()) {
            Animal animal = (Animal) entry.getValue();
            Set<Animal> generatedAnimals = generateAnimals(animal.getClass(), prototypes);
            animals.put(animal.getClass(), generatedAnimals);
        }

        Plant plants = generatePlants();
        return new Cell(gameField, Terrain.generateTerritory(), animals, plants);
    }

    private Plant generatePlants() {
        return new Plant(Dice.random(0, Plant.maxMassInCell));
    }

    private Set<Animal> generateAnimals(Class<? extends Animal> animalClass, Map<Type, Object> prototypes) {
        Set<Animal> result = new HashSet<>();
        Animal animal = (Animal) prototypes.get(animalClass);
        double probabilityOfAnimalSpawn = Dice.random(0, 1.0);
        boolean isSpawned = probabilityOfAnimalSpawn > Settings.settings.MIN_CHANCE_OF_SPAWN;
        if (isSpawned) {
            int animalAmount = Dice.random(0, animal.getAnimalLimits().getMaxPopulationInCell());
            for (int i = 0; i < animalAmount; i++) {
                try {
                    Animal clone = animal.clone();
                    result.add(clone);
                } catch (Exception e) {
                    throw new IslandGameException(e);
                }
            }
        }
        return result;
    }
}