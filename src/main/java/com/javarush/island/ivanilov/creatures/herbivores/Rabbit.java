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
public class Rabbit extends Herbivore {

    public Rabbit() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Кролик");
        setIcon("\uD83D\uDC07");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                1.55,
                2,
                150,
                2,
                0.45,
                0.3);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                4,
                4,
                12);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
