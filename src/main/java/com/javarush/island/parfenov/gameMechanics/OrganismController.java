package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.organisms.Organism;
import com.javarush.island.parfenov.statistics.Stat;

import java.util.*;

public class OrganismController implements Runnable {
    private final String organismName;
    private final Cell[][] field;
    private final Queue<Task> tasks = new LinkedList<>();
    private final Stat stat;
    public OrganismController(GameController gameController, String organismName, Stat stat) {
        this.organismName = organismName;
        field = gameController.getField();
        this.stat = stat;
    }

    @Override
    public void run() {
        stat.getAmountOfOrganisms().put(organismName, 0);
        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                cell.getVaultLock(organismName).lock();
                try {
                    int amountOfAnimals;
                    Set<Organism> organisms = cell.getCertainResidents(organismName);
                    int oldAmountOfAnimals = organisms.size();
                    organisms.addAll(cell.getCertainMigrants(organismName));
                    cell.getCertainMigrants(organismName).clear();
                    cell.setAmountOfMigrants(organismName, 0);
                    Iterator<Organism> iterator = organisms.iterator();
                    while(iterator.hasNext()) {
                        Organism organism = iterator.next();
                        if(!organism.isAlive() || (organism.getNextDestination() != null && !organism.getNextDestination().equals(cell))) {
                            iterator.remove();
                            Organism.amountOfAnimals.decrementAndGet();
                        } else {
                            tasks.add(new Task(organism, cell));
                        }
                    }
                    cell.setAmountOfResidents(organismName, organisms.size());
                    amountOfAnimals = organisms.size();
                    int delta = amountOfAnimals - oldAmountOfAnimals;
                    stat.getAmountOfOrganisms().put(organismName, stat.getAmountOfOrganisms().get(organismName) + amountOfAnimals);
                    stat.setFullSize(stat.getFullSize().get() + delta);
                    Organism.amountOfAnimals.addAndGet(delta);
                } finally {
                    cell.getVaultLock(organismName).unlock();
                }
            }
        }
        tasks.forEach(Task::execute);
        tasks.clear();
    }
}