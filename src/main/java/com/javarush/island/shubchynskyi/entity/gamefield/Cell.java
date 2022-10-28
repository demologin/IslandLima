package com.javarush.island.shubchynskyi.entity.gamefield;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.plants.Plant;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final Lock lock = new ReentrantLock(true);
    private final List<Cell> neighbours = new ArrayList<>();
    private final Map<String, Set<Animal>> animalsInCell = new HashMap<>();
    private final Map<String, Set<Plant>> plantsInCell = new HashMap<>();

    public Map<String, Set<Animal>> getAnimalsInCell() {
        return animalsInCell;
    }

    public Map<String, Set<Plant>> getPlantsInCell() {
        return plantsInCell;
    }

    public List<Cell> getNeighbours() {
        return neighbours;
    }

    public Lock getLock() {
        return lock;
    }

    public void addNeighbour(Cell cell) {
        neighbours.add(cell);
    }


    // For test only
    @Override
    public String toString() {
        List<String> plantsStatistic = new ArrayList<>();
        for (var var : plantsInCell.entrySet()) {
            plantsStatistic.add(var.getKey() + " : " + var.getValue().size());
        }
        List<String> animalsStatistic = new ArrayList<>();
        for (var var : animalsInCell.entrySet()) {
            animalsStatistic.add(var.getKey() + " : " + var.getValue().size());
        }

        return "Cell [" + x + "/" + y + "] //info: \n" +
                "\t" + "cell neighbours is " + getNeighbours().size() + "\n" +
                "\t" + "plants in cell: " +
                plantsStatistic + "\n" +
                "\t" + "animals in cell: " +
                animalsStatistic;
    }
}
