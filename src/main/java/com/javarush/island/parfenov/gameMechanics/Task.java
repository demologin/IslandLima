package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.organisms.Animal;
import com.javarush.island.parfenov.organisms.Organism;

public class Task {
    Organism organism;
    Cell cell;

    public Task(Organism organism, Cell cell) {
        this.organism = organism;
        this.cell = cell;
    }

    public void execute() {
        organism.multiply(cell);
        organism.changeWeightByTime();
        if (organism instanceof Animal) {
            organism.eat(cell);
            organism.move(cell);
        }
    }
}
