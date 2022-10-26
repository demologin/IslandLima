package com.javarush.island.ivanilov.creatures.herbivores;

import com.javarush.island.ivanilov.builders.AnimalScanner;
import com.javarush.island.ivanilov.entities.AnimalLimits;
import com.javarush.island.ivanilov.entities.BreedingParams;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
@SuppressWarnings("unused")
public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Гусеница");
        setIcon("\uD83D\uDC1B");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                0.005,
                0.01,
                1000,
                0,
                0.05,
                0.025);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                2,
                25,
                150);
        setBreedingParams(breedingParams);
    }
}
