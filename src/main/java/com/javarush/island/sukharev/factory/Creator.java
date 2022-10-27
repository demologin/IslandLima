package com.javarush.island.sukharev.factory;

import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.island.Cell;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.storage.DataBase;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;


public class Creator {

     public void start(Island island, DataBase dataBase, Random random) {

        EntityFactory entityFactory = EntityFactory.getEntityFactory();
        IntStream.range(0, dataBase.getIslandWidth())
                .forEach(i -> IntStream.range(0, dataBase.getIslandLength())
                        .forEach(j -> island.matrix[i][j] = new Cell(i, j,
                                        creatorAnimal(entityFactory, random, dataBase),
                                        creatorVegetation(entityFactory, random, dataBase))));
    }

    public Set<Vegetation> creatorVegetation(EntityFactory entityFactory, Random random, DataBase dataBase) {

        Set<Vegetation> vegetationlInCell = new CopyOnWriteArraySet<>();

        int numberObjects = random.nextInt((int) (dataBase.vegetationParameters()[1]));

        for (int i = 0; i < numberObjects; i++) {
            vegetationlInCell.add(entityFactory.readyVegetation(dataBase, numberObjects));
        }

        return vegetationlInCell;
    }

    public Set<Animal> creatorAnimal(EntityFactory entityFactory, Random random, DataBase dataBase) {

        Set<Animal> animalInCell = new CopyOnWriteArraySet<>();
        for (int i = 0; i < dataBase.animals.length; i++) {

            int numberObjects = random.nextInt((dataBase.getDoublesObject().get(i)[1].add(new BigDecimal("1.0"))).intValue());

            for (int j = 0; j < numberObjects; j++) {

                animalInCell.add(entityFactory.readyAnimal(dataBase.animals[i], i + 1,  dataBase));
            }
        }
        return animalInCell;
    }

    private static Creator creator;

    private Creator() {
    }

    public static synchronized Creator getCreator() {
        if (creator == null) {
            creator = new Creator();
            return creator;
        }
        return creator;
    }
}


