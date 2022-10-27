package com.javarush.island.alimin.entity.ecosystem.plants;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.properties.OrganismParameters;

import java.util.Set;

public class Plant extends Organism {

    public Plant(OrganismParameters parameters) {
        super(parameters);
    }

    @Override
    public boolean reproduce(Cell cell) {
        boolean result = false;
        Types animalType = Types.valueOf(this.getClass().getSimpleName().toUpperCase());
        Set<Organism> organisms = cell.getResidents().get(animalType);
        if (organisms.contains(this) && organisms.size() < this.getParameters().getMaxCount()) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isDead() {
        return this.getCurrentWeight() <= 0;
    }
}
