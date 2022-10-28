package com.javarush.island.alimin.entity.location;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.OrganismFactory;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.ecosystem.animals.Animal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final int row;
    private final int column;

    private final Lock lock = new ReentrantLock(true);

    private final Map<Types, Set<Organism>> residents = new HashMap<>();

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void simulate() {
        makeResidentsEat();
        makeResidentsReproduce();
        makeResidentsMove();
        removeDeadOrganisms();
    }

    private void makeResidentsEat() {
        if (residents.size() == 0) {
            return;
        }
        Set<Types> types = residents.keySet();
        boolean isEat;
        for (Types type : types) {
            if (type != Types.PLANT) {
                Set<Organism> organisms = residents.get(type);
                Set<Organism> organismsCopy = new HashSet<>(organisms);
                for (Organism organism : organismsCopy) {
                    Animal animal = (Animal) organism;
                    isEat = animal.eat(this);
                    if (!isEat) {
                        animal.loseWeight();
                    }
                }
            }
        }
    }

    private void makeResidentsReproduce() {
            if (residents.size() == 0) {
                return;
            }
            Set<Types> types = residents.keySet();
            for (Types type : types) {
                Set<Organism> organisms = residents.get(type);
                int offspringMax = organisms.size() / 2;
                int offspringCount = 0;
                Set<Organism> organismsCopy = new HashSet<>(organisms);
                for (Organism organism : organismsCopy) {
                    if (organism.reproduce(this) && offspringCount < offspringMax) {
                        organisms.add(OrganismFactory.getInstance().createOrganism(type, organism.getParameters()));
                        offspringCount++;
                    }
                }
            }
    }

    private void makeResidentsMove() {
            if (residents.size() == 0) {
                return;
            }
            Set<Types> types = residents.keySet();
            for (Types type : types) {
                if (type != Types.PLANT) {
                    Set<Organism> organisms = residents.get(type);
                    Set<Organism> organismsCopy = new HashSet<>(organisms);
                    for (Organism organism : organismsCopy) {
                        Animal animal = (Animal) organism;
                        animal.move(this);
                    }
                }
            }
    }

    private void removeDeadOrganisms() {
            Set<Types> types = residents.keySet();
            for (Types type : types) {
                Set<Organism> organisms = residents.get(type);
                organisms.removeIf(Organism::isDead);
            }
    }

    public Map<Types, Set<Organism>> getResidents() {
        return residents;
    }

    public Lock getLock() {
        return lock;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Cell[" + row + ", " + column + "]";
    }
}
