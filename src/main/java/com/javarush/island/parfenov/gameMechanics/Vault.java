package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.organisms.Organism;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Vault {
    private final Set<Organism> residents = new HashSet<>();
    private final Set<Organism> migrants = new HashSet<>();
    private int amountOfMigrants;
    private int amountOfResidents;
    private final ReentrantLock lock = new ReentrantLock(true);

    public Set<Organism> getResidents() {
        return residents;
    }


    public Set<Organism> getMigrants() {
        return migrants;
    }


    public int getAmountOfMigrants() {
        return amountOfMigrants;
    }

    public int getAmountOfResidents() {
        return amountOfResidents;
    }

    public void setAmountOfMigrants(int amountOfMigrants) {
        this.amountOfMigrants = amountOfMigrants;
    }

    public void setAmountOfResidents(int amountOfResidents) {
        this.amountOfResidents = amountOfResidents;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
