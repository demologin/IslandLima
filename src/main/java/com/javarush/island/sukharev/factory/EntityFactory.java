package com.javarush.island.sukharev.factory;

import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.storage.DataBase;

import java.math.BigDecimal;

public class EntityFactory {

    public Vegetation readyVegetation(DataBase dataBase, int numberObjects) {

        return dataBase.allVegetation();
    }

    public Animal readyAnimal(String animalName, int id, DataBase dataBase) {

        String icon = dataBase.giveMeAnIcon(id);

        BigDecimal[] animalParameters = dataBase.getDoublesObject().get(id - 1);

        int[] whoToEatAndHowToEat = dataBase.whoToEatAndHowToEat(id);

        return dataBase.allAnimal(animalName, icon, animalParameters[0], animalParameters[2].intValue(), animalParameters[3],
                whoToEatAndHowToEat, id);
    }

    private static EntityFactory entityFactory;

    private EntityFactory() {
    }

    public static synchronized EntityFactory getEntityFactory() {
        if (entityFactory == null) {
            entityFactory = new EntityFactory();
            return entityFactory;
        }
        return entityFactory;
    }

}
