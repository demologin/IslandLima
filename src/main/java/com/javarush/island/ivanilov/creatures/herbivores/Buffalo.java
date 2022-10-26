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
public class Buffalo extends Herbivore {

    public Buffalo() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Буйвол");
        setIcon("\uD83D\uDC03");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                600,
                700,
                10,
                3,
                100,
                75);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                45,
                1,
                1);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.RIVER);
    }
}
