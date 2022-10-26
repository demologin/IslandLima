package com.javarush.island.sternard.map;

import com.javarush.island.sternard.organisms.parents.Animal;
import com.javarush.island.sternard.organisms.parents.Organism;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Cell {
    private final int column;
    private final int row;
    private final List<Organism> organisms;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.organisms = new CopyOnWriteArrayList<>();
    }

    public void addOrganism(Organism organism) {
        this.organisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
        this.organisms.remove(organism);
    }

    public List<Animal> getAnimals() {
        return this.organisms.stream()
                .filter(Animal.class::isInstance)
                .map(Animal.class::cast)
                .toList();
    }
}
