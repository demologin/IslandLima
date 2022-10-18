package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.entity.organizms.animals.Animal;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class Task {

    private final Organism organism;
    private final Cell cell;

    public Task(Organism organism, Cell cell) {
        this.organism = organism;
        this.cell = cell;
    }

    public void execute() {
        organism.spawn(cell);
        if (organism instanceof Animal animal) {
            animal.eat(cell);
            animal.move(cell);
        }
    }


}
