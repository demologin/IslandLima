package com.javarush.island.khlopin.field;

import com.javarush.island.khlopin.settings.Preferences;
import com.javarush.island.khlopin.units.*;
import com.javarush.island.khlopin.units.herbivorous.Caterpillar;
import com.javarush.island.khlopin.units.herbivorous.Duck;
import com.javarush.island.khlopin.units.plant.Plant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class Cell {

    private final int row;
    private final int col;

    public Map<String, List<Unit>> sets = new HashMap<>();
    private final UnitDistributor unitDistributor = new UnitDistributor();


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void makeStep() {
        eat();

        checkForSatiety();

        move();

        multiply();
    }

    private void eat() {

        for (Map.Entry<String, List<Unit>> pair : sets.entrySet()) {
            List<Unit> value = pair.getValue();
            for (Unit unit : value) {
                if (unit instanceof Carnivores) {
                    List<Unit> herbivores = unitDistributor.getHerbivores();
                    for (Unit herbivore : herbivores) {
                        ((Carnivores) unit).eat(sets.get(herbivore.getClass().getSimpleName()), unit);
                    }

                } else if (unit instanceof Duck) {
                    List<Unit> herbivores = unitDistributor.getHerbivores();
                    for (Unit herbivore : herbivores) {
                    ((Duck) unit).eat(sets.get(herbivore.getClass().getSimpleName()), unit); }
                } else if (unit instanceof Herbivorous) {
                    List<Unit> plants = unitDistributor.getPlants();
                    for (Unit plant : plants) {
                        ((Herbivorous) unit).eat(sets.get(plant.getClass().getSimpleName()), unit);
                    }
                }
            }
        }

    }

    private void checkForSatiety() {
        for (Map.Entry<String, List<Unit>> pair : sets.entrySet()) {
            List<Unit> value = pair.getValue();
            Iterator<Unit> iterator = value.iterator();
            while (iterator.hasNext()) {
                if (iterator instanceof Plant) {
                    continue;
                }
                double foodForSaturationCurrent = iterator.next().getProperties().foodForSaturation;
                if (foodForSaturationCurrent < 0) {
                    iterator.remove();
                }
            }
        }
    }

    private void move() {
        boolean isMove = true;
        for (Map.Entry<String, List<Unit>> pair : sets.entrySet()) {
            List<Unit> value = pair.getValue();
            for (Unit unit : value) {
                if (unit instanceof Plant || unit instanceof Caterpillar) {
                    continue;
                }
                for (Cell[] cells : GameField.field) {
                    for (Cell cell : cells) {

                        int newRow = cell.getCol() + ThreadLocalRandom.current().nextInt(0, unit.getProperties().maxSpeed);
                        int newCol = cell.getRow() + ThreadLocalRandom.current().nextInt(0, unit.getProperties().maxSpeed);


                        if (newRow >= Preferences.Y || newCol >= Preferences.X || newCol < 0 || newRow < 0) {
                            isMove = false;
                        }
                        if (newRow >= getRow() && newCol >= getCol()) {
                            isMove = false;
                        }
                        if (isMove) {
                            Map<String, List<Unit>> setsOld = GameField.field[cell.getRow()][cell.getCol()].sets;
                            Map<String, List<Unit>> setsNew = GameField.field[newRow][newCol].sets;
                            setsOld.forEach(setsNew::putIfAbsent);

                        }
                    }
                }
            }
        }
    }

    private void multiply() {
        for (Map.Entry<String, List<Unit>> pair : sets.entrySet()) {
            String name = pair.getKey();
            List<Unit> units = pair.getValue();
            if (units != null) {
                int numberOfChildren = units.size() / 2;
                for (int i = 0; i < numberOfChildren; i++) {
                    Unit unit = UnitFactory.bornUnitByName(name);
                    units.add(unit);
                }
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
