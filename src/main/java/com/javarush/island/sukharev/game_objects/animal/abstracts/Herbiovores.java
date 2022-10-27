package com.javarush.island.sukharev.game_objects.animal.abstracts;

import com.javarush.island.sukharev.factory.EntityFactory;
import com.javarush.island.sukharev.game_objects.animal.herbivores.Rabbit;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.storage.DataBase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

public abstract class Herbiovores extends Animal {

    int[] whoToEatAndHowToEat;
    BigDecimal maximumSatiety;
    private final int speedCell;


    protected Herbiovores(String icon, BigDecimal weight, int speedCell, int id, BigDecimal maximumSatiety, int[] whoToEatAndHowToEat) {
        super(icon, weight, id);
        this.maximumSatiety = maximumSatiety;
        this.whoToEatAndHowToEat = whoToEatAndHowToEat;
        this.speedCell = speedCell;
    }


    @Override
    public void eat(Set<Animal> animalSet, Set<Vegetation> vegetationSet, Random random, DataBase dataBase) {
         this.setIEat(false);

        for (int i = 0; i < DataBase.ATTEMPTS; i++) {

            Stream<Vegetation> tipStream = vegetationSet.parallelStream();

            int randomNumber = random.nextInt(vegetationSet.size());

            List<Vegetation> vegetationList = tipStream.skip(randomNumber)
                    .toList();

            BigDecimal weightAnimal = vegetationList.get(0).getWeight();
            BigDecimal weightThis = this.getWeight();

            while (youCanEatVegetation(vegetationSet, dataBase, weightAnimal, weightThis)) {

                weightAnimal = weightAnimal.subtract(new BigDecimal("0.01"));
                weightThis = weightThis.add(new BigDecimal("0.01"));

            }
        }


        this.setIEat(true);

        for (int i = 0; i < DataBase.ATTEMPTS; i++) {

            Stream<Animal> tipStream = animalSet.parallelStream();
            int randomNumber = random.nextInt(animalSet.size());
            Animal animal = tipStream.skip(randomNumber)
                    .toList()
                    .get(0);

            BigDecimal weightAnimal = animal.getWeight();
            BigDecimal weightThis = this.getWeight();

            while (youCanEatAnimal(dataBase, animal, weightAnimal, weightThis)) {

                animal.setTheyEatMe(false);
                this.setIEat(false);

                weightAnimal = weightAnimal.subtract(new BigDecimal("0.01"));
                weightThis = weightThis.add(new BigDecimal("0.01"));
            }

            animal.setWeight(weightAnimal);
            this.setWeight(weightThis);
            this.setIEat(true);

        }
    }

    private boolean youCanEatAnimal(DataBase dataBase, Animal animal, BigDecimal weightAnimal, BigDecimal weightThis) {
        return this != animal &&
                this.isIEat() &&
                this.isIHaveMultiplied() &&
                this.isTheyEatMe() &&
                this.isiWent() &&
                animal.isIEat() &&
                animal.isIHaveMultiplied() &&
                animal.isiWent() &&
                (dataBase.getDoublesObject().get(this.getId() - 1)[0].add(dataBase.getDoublesObject().get(this.getId() - 1)[3])).compareTo(weightThis) >= 0 &&
                weightAnimal.compareTo(new BigDecimal("0")) > 0 &&
                0 < whoToEatAndHowToEat[animal.getId() - 1];
    }

    private boolean youCanEatVegetation(Set<Vegetation> vegetationSet, DataBase dataBase, BigDecimal weightAnimal, BigDecimal weightThis) {
        return !vegetationSet.isEmpty() &&
                this.isIEat() &&
                this.isIHaveMultiplied() &&
                this.isTheyEatMe() &&
                this.isiWent() &&
                weightAnimal.compareTo(new BigDecimal("0")) > 0 &&
                (dataBase.getDoublesObject().get(this.getId() - 1)[0].add(dataBase.getDoublesObject().get(this.getId() - 1)[3])).compareTo(weightThis) >= 0;
    }

    //=============================================animal breeds========================================================

    @Override
    public Set<Animal> reproduction(Island island, Set<Animal> animalSet, Random random, DataBase dataBase, EntityFactory entityFactory) {

        Set<Animal> newborns = new CopyOnWriteArraySet<>();

        int attempts = DataBase.ATTEMPTS_REPRODUCTION;

        for (int i = 0; i < attempts; i++) {

            Stream<Animal> tipStream = animalSet.parallelStream();
            int randomNumber = random.nextInt(animalSet.size());
            Animal animal = tipStream.skip(randomNumber)
                    .toList()
                    .get(0);

            if (youCanBreed(animal)) {

                this.setIHaveMultiplied(false);
                animal.setIHaveMultiplied(false);

                int attempt = random.nextInt(dataBase.numberOfOffspring[this.getId() - 1]);

                for (int j = 0; j < attempt; j++) {

                    Animal newAnimal = entityFactory.readyAnimal(dataBase.animals[this.getId() - 1], this.getId(), dataBase);

                    newborns.add(newAnimal);

                }
            }
        }
        return newborns;
    }

    private boolean youCanBreed(Animal animal) {
        return animal instanceof Rabbit &&
                animal.isIEat() &&
                animal.isIHaveMultiplied() &&
                animal.isTheyEatMe() &&
                animal.isiWent() &&
                this.isIEat() &&
                this.isIHaveMultiplied() &&
                this.isTheyEatMe() &&
                this.isiWent();
    }

    //=============================================the animal is moving========================================================

    @Override
    public int[] wentToAnotherCell(Island island, Random random, DataBase dataBase, int x, int y) {

        int[] newCoordinates = {x, y};
        if (this.isIEat() && this.isIHaveMultiplied() && this.isTheyEatMe() && this.isiWent()) {
            this.setiWent(false);

            for (int i = 0; i < this.speedCell; i++) {

                if (random.nextInt(2) == 1) {

                    if (random.nextInt(2) == 1) {
                        ++x;
                        if (x >= dataBase.getIslandWidth()) {
                            x -= 2;
                        }
                    } else {
                        --x;
                        if (x < 0) {
                            x += 2;
                        }
                    }
                } else {
                    if (random.nextInt(2) == 1) {
                        ++y;
                        if (y >= dataBase.getIslandLength()) {
                            y -= 2;
                        }
                    } else {
                        --y;
                        if (y < 0) {
                            y += 2;
                        }
                    }
                }
            }
            newCoordinates[0] = x;
            newCoordinates[1] = y;
            this.setiWent(true);
            return newCoordinates;
        }
        return newCoordinates;
    }
}
