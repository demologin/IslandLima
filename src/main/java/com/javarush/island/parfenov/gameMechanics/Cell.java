package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.organisms.Organism;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final Map<String, Vault> persons = new HashMap<>();
    private final List<Cell> cellsNearby = new ArrayList<>();

    public List<Cell> getCellsNearby() {
        return cellsNearby;
    }

    public Map<String, Vault> getPersons() {
        return persons;
    }

    public Set<Organism> getCertainResidents(String name) {
        return persons.get(name).getResidents();
    }

    public Set<Organism> getCertainMigrants(String name) {
        return persons.get(name).getMigrants();
    }

    public ReentrantLock getVaultLock(String name) {
        return persons.get(name).getLock();
    }

    public int getAmountOfMigrants(String name) {
        return persons.get(name).getAmountOfMigrants();
    }

    public void setAmountOfMigrants(String name, int amount) {
        persons.get(name).setAmountOfMigrants(amount);
    }

    public void setAmountOfResidents(String name, int amount) {
        persons.get(name).setAmountOfResidents(amount);
    }
}
