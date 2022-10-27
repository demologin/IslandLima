package com.javarush.island.akhundov.services;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.field.Cell;
import com.javarush.island.akhundov.field.GameField;

import java.util.ArrayList;
import java.util.Map;

public class MoveService extends AbstractService{

    public MoveService(GameField gameField){
        this.gameField = gameField;
    }

    @Override
    public void cellAction(Cell cell) {
        cell.getLock().lock();
        try {
            Cell tempcell;
            for (Map.Entry<Class<?>, ArrayList<Animal>> pair : cell.getAnimalsMap().entrySet()) {
                ArrayList<Animal> animalList = new ArrayList<>(pair.getValue());
                for (Animal animal : animalList) {
                    tempcell = cell;
                    for (int i = 0; i < animal.getSpeed(); i++) {
                        tempcell = makeStep(tempcell, animal);
                    }
                }
            }
        }finally {
            cell.getLock().unlock();
        }
    }

    private Cell makeStep(Cell cell, Animal animal){
        Cell nextCell = animal.move(cell);
        nextCell.getLock().lock();
        try {
            nextCell.getAnimalsMap().get(animal.getClass()).add(animal);
            cell.getAnimalsMap().get(animal.getClass()).remove(animal);
        }finally {
            nextCell.getLock().unlock();
        }
        return nextCell;
    }
}
