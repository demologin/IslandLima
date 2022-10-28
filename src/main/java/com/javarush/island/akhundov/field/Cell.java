package com.javarush.island.akhundov.field;

import com.javarush.island.akhundov.animals.Animal;
import com.javarush.island.akhundov.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private final int row;
    private final int col;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final HashMap<Class<?>, ArrayList<Animal>> animalsMap = new HashMap<>();
    private final HashMap<String, ArrayList<Plant>> plantsMap = new HashMap<>();
    private final ArrayList<Cell> possibleDirections = new ArrayList<>();

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }

    public ReentrantLock getLock() {
        return reentrantLock;
    }

    public HashMap<Class<?>, ArrayList<Animal>> getAnimalsMap() {
        return animalsMap;
    }
    public HashMap<String, ArrayList<Plant>> getPlantsMap() {
        return plantsMap;
    }
    public ArrayList<Cell> getDirectionList() {
        return possibleDirections;
    }
}
