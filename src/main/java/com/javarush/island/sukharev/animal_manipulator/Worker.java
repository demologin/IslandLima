package com.javarush.island.sukharev.animal_manipulator;

import com.javarush.island.sukharev.factory.EntityFactory;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.storage.DataBase;

import java.util.Random;

public class Worker implements Runnable {
    Island island;
    Random random;
    int width;
    int length;
    DataBase dataBase;
    EntityFactory entityFactory;
    AssistantManager assistantManager;

    public Worker(Island island, Random random, int width, int length, DataBase dataBase, EntityFactory entityFactory, AssistantManager assistantManager) {
        this.island = island;
        this.random = random;
        this.width = width;
        this.length = length;
        this.dataBase = dataBase;
        this.entityFactory = entityFactory;
        this.assistantManager = assistantManager;
    }

    @Override
    public void run() {
        assistantManager.animalsEat(island, random, width, length, dataBase);

        assistantManager.animalsReproduction(island, random, width, length, dataBase, entityFactory);

        assistantManager.moving(island, random, width, length, dataBase);
    }
}
