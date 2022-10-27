package com.javarush.island.sukharev.animal_manipulator;

import com.javarush.island.sukharev.factory.EntityFactory;
import com.javarush.island.sukharev.factory.Settings;
import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.island.Island;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.report.Statistics;
import com.javarush.island.sukharev.storage.DataBase;
import com.javarush.island.sukharev.storage.ProgramCommunication;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class AssistantManager {

    protected void startTheGame
            (Statistics statistics, Island island, Settings settings, ProgramCommunication pc, boolean language,
             int width, int length) {
        System.out.println(pc.seeCellByCoordinates(language));
        if (settings.flagRegulator(pc, language)) {
            System.out.println(pc.coordinateEntryConditions(language));
            System.out.println(pc.cellСoordinateШnput(language));
            System.out.println("В пределах от 0 до " + width);
            System.out.print("X = ");
            int pperLimit = 0;
            int x = settings.letAnswer(pc, language, pperLimit, width).intValue();
            System.out.println("В пределах от 0 до " + length);
            System.out.print("Y = ");
            int y = settings.letAnswer(pc, language, pperLimit, length).intValue();

            statistics.printTheCage(island, x, y);
        } else {
            statistics.printTheCage(island, 0, 0);
            statistics.printTheCage(island, 0, 1);
            statistics.printTheCage(island, 1, 0);
        }
    }

    protected void seeCage
            (Settings settings, ProgramCommunication pc, boolean language, int width, int length, Island island,
             AssistantManager assistantManager, Statistics statistics) {
        System.out.println(pc.seeTheCageOfTheIsland(language));

        if (settings.flagRegulator(pc, language)) {
            assistantManager.startTheGame(statistics, island, settings, pc, language, width, length);
        }
    }

    public void animalsEat(Island island, Random random, int width, int length, DataBase dataBase) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                Set<Animal> animalList = island.findCage(i, j).getAnimalSet();
                Set<Vegetation> vegetationList = island.findCage(i, j).getGrassSet();
                for (Animal animal : animalList) {
                    animal.eat(animalList, vegetationList, random, dataBase);
                }
            }
        }
    }

    public void animalsReproduction(Island island, Random random, int width, int length, DataBase dataBase, EntityFactory entityFactory) {
        System.out.println();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {

                Set<Animal> newbornsAnimal = new CopyOnWriteArraySet<>();

                Set<Animal> animals = island.findCage(i, j).getAnimalSet();

                for (Animal animal : animals) {

                    Set<Animal> newborns = animal.reproduction(island, island.findCage(i, j).getAnimalSet(), random, dataBase, entityFactory);
                    newbornsAnimal.addAll(newborns);
                }
                island.findCage(i, j).getAnimalSet().addAll(newbornsAnimal);
                newbornsAnimal.clear();
            }
        }
    }

    public void moving(Island island, Random random, int width, int length, DataBase dataBase) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                Set<Animal> animalList = island.findCage(i, j).getAnimalSet();
                //Iterator
                for (Animal animal : animalList) {
                    int[] newCoordinates = animal.wentToAnotherCell(island, random, dataBase, i, j);
                    animalList.remove(animal);
                    island.findCage(newCoordinates[0], newCoordinates[1]).getAnimalSet().add(animal);

                }
            }
        }
    }

    public void dies(Island island, int width, int length, DataBase dataBase) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                Set<Animal> animalList = island.findCage(i, j).getAnimalSet();
                animalList.removeIf(animal ->  dataBase.animalParameters(animal.getId())[0].compareTo(animal.getWeight()) > 0);
            }
        }
    }

    private static AssistantManager assistantManager;

    private AssistantManager() {
    }

    public static synchronized AssistantManager getAssistantManager() {
        if (assistantManager == null) {
            assistantManager = new AssistantManager();
            return assistantManager;
        }
        return assistantManager;
    }
}
