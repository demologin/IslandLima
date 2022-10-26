package com.javarush.island.ivanilov.creatures.herbivores;

import com.javarush.island.ivanilov.builders.AnimalScanner;
import com.javarush.island.ivanilov.entities.AnimalLimits;
import com.javarush.island.ivanilov.entities.BreedingParams;
import com.javarush.island.ivanilov.entities.Terrain;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
@SuppressWarnings("unused")
public class Horse extends Herbivore {

    public Horse() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Лошадь");
        setIcon("\uD83D\uDC0E");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                340,
                400,
                20,
                4,
                60,
                20);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                50,
                1,
                2);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.MOUNTAINS);
    }
}
