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
public class Wolf extends Carnivore {

    public Wolf() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Волк");
        setIcon("\uD83D\uDC3A");
        AnimalLimits animalLimits = new AnimalLimits(
                5,
                42,
                50,
                30,
                3,
                8,
                1.5);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                10,
                4,
                8);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Horse.class, 10);
        foodPreferences.put(Deer.class, 15);
        foodPreferences.put(Rabbit.class, 60);
        foodPreferences.put(Mouse.class, 80);
        foodPreferences.put(Goat.class, 60);
        foodPreferences.put(Sheep.class, 70);
        foodPreferences.put(Boar.class, 15);
        foodPreferences.put(Buffalo.class, 10);
        foodPreferences.put(Duck.class, 40);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
