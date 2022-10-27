package com.javarush.island.dobrov.Field;

import com.javarush.island.dobrov.Animal;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    int row;
    int col;

    public Map <String, Set<Animal>> animals = new HashMap<>();

    private static final Lock lock = new ReentrantLock(true);


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Map<String, Set<Animal>> getAnimals() {
        return animals;
    }


    public Lock getLock(){
        return lock;
    }




    @Override
    public String toString() {
        return "Cell{" +
                "x=" + row +
                ", y=" + col +
                '}';
    }

}
