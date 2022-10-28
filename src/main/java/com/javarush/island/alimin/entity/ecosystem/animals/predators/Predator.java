package com.javarush.island.alimin.entity.ecosystem.animals.predators;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.ecosystem.animals.Animal;
import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.properties.OrganismParameters;

import java.util.Iterator;
import java.util.Set;

public abstract class Predator extends Animal {
    public Predator(OrganismParameters parameters) {
        super(parameters);
    }

    @Override
    public boolean eat(Cell cell) {
            boolean result = false;
            if (this.getCurrentWeight() < getParameters().getMaxWeight()) {
                Set<Types> herbivorousTypes = Types.getHerbivorous();
                double neededFood = this.getParameters().getMaxWeight() - this.getCurrentWeight();
                double eatenFood = 0;
                for (Types type : herbivorousTypes) {
                    Set<Organism> organisms = cell.getResidents().get(type);
                    if (organisms != null) {
                        Iterator<Organism> iterator = organisms.iterator();
                        while (neededFood > 0 && iterator.hasNext() && eatenFood < this.getParameters().getMaxFood()) {
                            Organism organism = iterator.next();
                            if (this.hunt(organism)){continue;}
                            double organismCurrentWeight = organism.getCurrentWeight();
                            if (neededFood >= organismCurrentWeight) {
                                eatenFood += organismCurrentWeight;
                                neededFood -= organismCurrentWeight;
                                iterator.remove();
                            } else {
                                eatenFood += neededFood;
                                iterator.remove();
                                neededFood -= neededFood;
                            }
                        }
                    }
                }
                if (eatenFood <= 0) {
                    return false;
                }
                this.setCurrentWeight(this.getCurrentWeight() + eatenFood);
                result = true;
            }
            return result;
    }
}
