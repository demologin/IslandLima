package com.javarush.island.torkel.map;

import com.javarush.island.torkel.entity.Entity;
import com.javarush.island.torkel.entity.EntityType;
import com.javarush.island.torkel.entity.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.javarush.island.torkel.Config.COLUMNS;
import static com.javarush.island.torkel.Config.ROWS;

public class GameMap {
    Cell[][] cells = new Cell[ROWS][COLUMNS];

//    List<Entity> entityList = new ArrayList<>();

    public List<Entity> createRandomList() {
        List<Entity> result = new ArrayList<>();
        for (EntityType entityType : EntityType.values()) {

            int maxCount = entityType.getMaxCount();
            int randomCount = new Random().nextInt(maxCount);

            for (int i = 0; i < randomCount; i++) {

                result.add(Entity.createEntity(entityType));

            }
        }
        return result;
    }

    public GameMap() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
//                List<Animal> animalsToCell = new ArrayList<>();
                cells[i][j] = new Cell(createRandomList());
            }

        }
    }

    public void printGameMap() {
        List<Entity> allEntity = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                cells[i][j].print();
                allEntity.addAll(cells[i][j].getEntities());

            }
            System.out.print("\n");
//            System.out.println(("\n"));


        }
//        System.out.println();
        for (EntityType type : EntityType.values()) {
            long countType = allEntity.stream().filter(entity -> entity.getType() == type).count();
            System.out.print(type.getIcon() + " = " + countType + " ");
        }
        System.out.println("\n");
    }

    public void iterate() throws InterruptedException {
        int countOfCore = Runtime.getRuntime().availableProcessors();
        List<Thread> threadList = new ArrayList<>();

        for (int k = 0; k < countOfCore; k++) {
            final int index = k;
            threadList.add(new Thread(() -> start(countOfCore, index), String.valueOf(k)));

        }
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }

    }

    private void start(int countOfCore, int index) {
        for (int i = 0; i < cells.length; i++) {
            if (i % countOfCore == index) {
//                System.out.println("Я " + Thread.currentThread().getName() + " обрабатываю " + i + " строку");
                for (int j = 0; j < cells[0].length; j++) {
                    List<Entity> entityList = cells[i][j].iterate();
                    List<Entity> listAnimals = entityList.stream().filter(entity -> (entity instanceof Animal)).toList();
                    for (Entity animal : listAnimals) {
                        int maxSpeed = animal.getType().getMaxSpeed() + 1;
                        int random = new Random().nextInt(maxSpeed);
                        int verticalDirection = new Random().nextInt(3) - 1;
                        int horizontalDirection = new Random().nextInt(3) - 1;
                        int newI = (i + random * horizontalDirection);
                        int newJ = (j + random * verticalDirection);
                        if (newI < 0) {
                            newI = 0;
                        }
                        if (newJ < 0) {
                            newJ = 0;
                        }
                        if ((newI > cells.length - 1)) {
                            newI = cells.length - 1;
                        }
                        if (newJ > cells[0].length - 1) {
                            newJ = cells[0].length - 1;
                        }
                        cells[newI][newJ].add(animal);
                    }

                }
            }
        }
    }
}
