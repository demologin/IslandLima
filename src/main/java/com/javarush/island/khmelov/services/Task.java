package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.organizms.Organism;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class Task {

    private final Organism organism;
    private final Consumer<Organism> operation;

    public Task(Organism organism, Consumer<Organism> operation) {
        this.organism = organism;
        this.operation = operation;
    }

    public void run() {
        operation.accept(organism);
    }


}
