package com.javarush.island.alimin.entity.ecosystem.animals.herbivores;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.ecosystem.animals.Animal;
import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.properties.OrganismParameters;

import java.util.Iterator;
import java.util.Set;

public abstract class Herbivorous extends Animal {
    public Herbivorous(OrganismParameters parameters) {
        super(parameters);
    }

    @Override
    public boolean eat(Cell cell) {
            boolean result = false;
            if (this.getCurrentWeight() < getParameters().getMaxWeight()) {
                Set<Types> types = cell.getResidents().keySet();
                for (Types type : types) {
                    if (type == Types.PLANT) {
                        Set<Organism> organisms = cell.getResidents().get(type);
                        Iterator<Organism> iterator = organisms.iterator();
                        double neededFood = this.getParameters().getMaxWeight() - this.getCurrentWeight();
                        double eatenFood = 0;
                        while (neededFood > 0 && iterator.hasNext() && eatenFood < this.getParameters().getMaxFood()) {
                            Organism organism = iterator.next();
                            double organismCurrentWeight = organism.getCurrentWeight();
                            if (neededFood >= organismCurrentWeight) {
                                eatenFood += organismCurrentWeight;
                                neededFood -= organismCurrentWeight;
                                iterator.remove();
                            } else {
                                eatenFood += neededFood;
                                organism.setCurrentWeight(organismCurrentWeight - neededFood);
                                neededFood -= neededFood;
                                if (organism.isDead()) {
                                    iterator.remove();
                                }
                            }
                        }
                        if (eatenFood <= 0) {
                            return false;
                        }
                        this.setCurrentWeight(this.getCurrentWeight() + eatenFood);
                        result = true;
                    }
                }
            }
            return result;
    }
}
