package com.javarush.island.ivanilov.creatures.carnivores;

import com.javarush.island.ivanilov.builders.AnimalScanner;
import com.javarush.island.ivanilov.creatures.herbivores.Caterpillar;
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
public class Fox extends Carnivore {

    public Fox() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Лиса");
        setIcon("\uD83E\uDD8A");
        AnimalLimits animalLimits = new AnimalLimits(
                6,
                6,
                8,
                30,
                2,
                2,
                0.5);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                8,
                3,
                6);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Rabbit.class, 70);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Duck.class, 60);
        foodPreferences.put(Caterpillar.class, 40);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
