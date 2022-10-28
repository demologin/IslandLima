package com.javarush.island.akhundov.utility;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.animals.herbivores.Herbivore;
import com.javarush.island.akhundov.animals.predators.Predator;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;

import java.util.ArrayList;
import java.util.Map;

public class Printer {
    GameField gameField;
    Preferences preferences;
    int herbCount;
    int predCount;
    int grassCount;

    public Printer(GameField gameField, Preferences preferences) {
        this.gameField = gameField;
        this.preferences = preferences;
    }

    public boolean printMap() {
        boolean allDead = true;
        boolean cellDead;

        for (int i = 0; i < gameField.getHeight(); i++) {
            for (int j = 0; j < gameField.getWidth(); j++) {
                cellDead = printCell(gameField.field[i][j]);
                if (!cellDead) {
                    allDead = false;
                }
            }
            System.out.println();
        }
        allCount();
        System.out.println();
        System.out.printf("Herbivores = %d , predators = %d, grass = %d",herbCount,predCount,grassCount);
        System.out.println();
        System.out.println();
        return allDead;
    }

    public boolean printCell(Cell cell) {
        Class<?> type = findFrequent(cell);
        if (type != null) {
            String name = returnName(type);
            System.out.print(name + "  ");
            return false;
        } else {
            System.out.print("Dead" + "  ");
            return true;
        }
    }

    private Class<?> findFrequent(Cell cell) {
        int frequencyCounter = 0;
        Class<?> type = null;

        for (Map.Entry<Class<?>, ArrayList<Animal>> pair : cell.getAnimalsMap().entrySet()) {
            if (pair.getValue().size() > frequencyCounter) {
                frequencyCounter = pair.getValue().size();
                type = pair.getKey();
            }
        }
        return type;
    }

    private void allCount() {
        herbCount = 0;
        predCount = 0;
        grassCount = 0;

        for (int i = 0; i < gameField.getHeight(); i++) {
            for (int j = 0; j < gameField.getWidth(); j++) {
                for (Map.Entry<Class<?>, ArrayList<Animal>> pair : gameField.field[i][j].getAnimalsMap().entrySet()) {
                    for (Animal animal : pair.getValue()) {
                        if (animal instanceof Herbivore) {
                            herbCount++;
                        }
                        if (animal instanceof Predator) {
                            predCount++;
                        }
                    }
                }

                grassCount += gameField.field[i][j].getPlantsMap().get("Grass").size();
            }
        }
    }

    private String returnName (Class < ? > type){
        return preferences.getNamesMap().get(type);
    }
}