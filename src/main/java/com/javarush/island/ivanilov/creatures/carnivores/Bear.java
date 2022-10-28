package com.javarush.island.ivanilov.creatures.carnivores;

import com.javarush.island.ivanilov.behaviours.EatingCarrion;
import com.javarush.island.ivanilov.builders.AnimalScanner;
import com.javarush.island.ivanilov.creatures.herbivores.*;
import com.javarush.island.ivanilov.entities.AnimalLimits;
import com.javarush.island.ivanilov.entities.BreedingParams;
import com.javarush.island.ivanilov.entities.Terrain;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
@EatingCarrion
@SuppressWarnings("unused")
public class Bear extends Carnivore {

    public Bear() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Медведь");
        setIcon("\uD83D\uDC3B");
        AnimalLimits animalLimits = new AnimalLimits(
                3,
                420,
                500,
                5,
                2,
                80,
                10);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                        28,
                        2,
                        5);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Boa.class, 80);
        foodPreferences.put(Horse.class, 40);
        foodPreferences.put(Deer.class, 80);
        foodPreferences.put(Rabbit.class, 80);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Goat.class, 70);
        foodPreferences.put(Sheep.class, 70);
        foodPreferences.put(Boar.class, 50);
        foodPreferences.put(Buffalo.class, 20);
        foodPreferences.put(Duck.class, 10);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
