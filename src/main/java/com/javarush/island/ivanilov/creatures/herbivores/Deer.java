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
public class Deer extends Herbivore {

    public Deer() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Олень");
        setIcon("\uD83E\uDD8C");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                250,
                300,
                20,
                4,
                50,
                25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                34,
                1,
                5);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.RIVER);
    }
}
