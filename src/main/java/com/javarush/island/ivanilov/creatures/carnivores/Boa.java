package com.javarush.island.ivanilov.creatures.carnivores;

import com.javarush.island.ivanilov.builders.AnimalScanner;
import com.javarush.island.ivanilov.creatures.herbivores.Rabbit;
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
@SuppressWarnings("unused")
public class Boa extends Carnivore {

    public Boa() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Удав");
        setIcon("\uD83D\uDC0D");
        AnimalLimits animalLimits = new AnimalLimits(
                3,
                12,
                15,
                30,
                1,
                3,
                0.25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                17,
                5,
                50);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Fox.class, 15);
        foodPreferences.put(Rabbit.class, 20);
        foodPreferences.put(Mouse.class, 40);
        foodPreferences.put(Duck.class, 10);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.MOUNTAINS);
    }
}
