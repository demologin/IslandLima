package com.javarush.island.torkel.map;

import com.javarush.island.torkel.Preferences;
import com.javarush.island.torkel.entity.Entity;
import com.javarush.island.torkel.entity.animal.Animal;
import com.javarush.island.torkel.entity.animal.herbivore.Herbivore;
import com.javarush.island.torkel.entity.animal.predator.Predator;
import com.javarush.island.torkel.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.Class;

public class Cell {
    public Cell(List<Entity> animalsInCell) {
        this.newEntityList = animalsInCell;
        this.entities = animalsInCell;
    }

    private List<Entity> newEntityList;
    private List<Entity> entities = new ArrayList<>();

    public void add(Entity entity) {
        newEntityList.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }


    public void print() {
        long predatorCount = entities.stream().filter(entity -> entity instanceof Predator).count();
        long herbivoreCount = entities.stream().filter(entity -> entity instanceof Herbivore).count();
        long plantCount = entities.stream().filter(entity -> entity instanceof Plant).count();

        System.out.print("| " + "P- " + predatorCount + " H -" + herbivoreCount + " Pl-" + plantCount + "\t\t\t");

    }

    public List<Entity> iterate() {
        this.entities = newEntityList;
        newEntityList = new ArrayList<>();
        hunt();
        changeWeight();

        multiply();
        return entities;
    }

    private void hunt() {

        Preferences preferences = Preferences.getInstance();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);
            for (int j = 0; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);

                if ((entity1 != entity2) && (entity1 instanceof Animal)) {
                    int probability;
                    try {
                        Map<Class, Map<Class, Integer>> probabilityMatrix = preferences.getMapProbability();
                        Map<Class, Integer> probabilityLine = probabilityMatrix.get(entity1.getClass());
                        probability = probabilityLine.get(entity2.getClass());
                    } catch (NullPointerException e) {
                        probability = 0;
                    }
                    int randomProbability = new Random().nextInt(100);
                    if (randomProbability < probability) {
                        entities.remove(entity2);
                        j--;
                        boolean isSatisfied = ((Animal) entity1).eat(entity2);
                        if (isSatisfied) {
                            break;
                        }


                    }

                }

            }
        }
    }

    private void changeWeight() {
        int count = entities.size();
        for (int i = 0; i < count; ) {
            entities.get(i).changeWeight();
            if (entities.get(i).isDie()) {
                entities.remove(i);
                count--;
            } else {
                i++;
            }
        }
    }

    private void multiply() {
        int count = entities.size();
        for (int i = 0; i < count; i++) {
            Entity entity = entities.get(i).multiply();
            if (entity != null) {
                entities.add(entity);
            }
        }
    }
}


