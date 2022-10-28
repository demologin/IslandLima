package com.javarush.island.alimin.entity.ecosystem.animals.herbivores;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.properties.OrganismParameters;

import java.util.Iterator;
import java.util.Set;

public class Mouse extends Herbivorous{
    public Mouse(OrganismParameters parameters) {
        super(parameters);
    }

    @Override
    public boolean eat(Cell cell) {
            boolean result = false;
            if (this.getCurrentWeight() < getParameters().getMaxWeight()) {
                Set<Types> types = cell.getResidents().keySet();
                double eatenFood = 0;
                double neededFood = this.getParameters().getMaxWeight() - this.getCurrentWeight();
                for (Types type : types) {
                    if (neededFood > 0 && type == Types.PLANT) {
                        Set<Organism> organisms = cell.getResidents().get(type);
                        Iterator<Organism> iterator = organisms.iterator();
                        double eatenPlants = eatPlants(iterator, neededFood, eatenFood);
                        neededFood -= eatenPlants;
                        eatenFood += eatenPlants;
                    }
                    if (neededFood > 0 && type == Types.CATERPILLAR) {
                        Set<Organism> organisms = cell.getResidents().get(type);
                        Iterator<Organism> iterator = organisms.iterator();
                        double eatenOrganisms = eatOrganisms(iterator, neededFood, eatenFood);
                        neededFood -= eatenOrganisms;
                        eatenFood += eatenOrganisms;
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

    private double eatPlants(Iterator<Organism> iterator, double neededFood, double eatenFood) {
        double currentEat = 0;
        while (neededFood > 0 && iterator.hasNext() && eatenFood < this.getParameters().getMaxFood()) {
            Organism organism = iterator.next();
            double organismCurrentWeight = organism.getCurrentWeight();
            if (neededFood >= organismCurrentWeight) {
                eatenFood += organismCurrentWeight;
                neededFood -= organismCurrentWeight;
                currentEat += organismCurrentWeight;
                iterator.remove();
            } else {
                eatenFood += neededFood;
                organism.setCurrentWeight(organismCurrentWeight - neededFood);
                currentEat += neededFood;
                neededFood -= neededFood;
                if (organism.isDead()) {
                    iterator.remove();
                }
            }
        }
        return currentEat;
    }

    private double eatOrganisms(Iterator<Organism> iterator, double neededFood, double eatenFood) {
        double currentEat = 0;
        while (neededFood > 0 && iterator.hasNext() && eatenFood < this.getParameters().getMaxFood()) {
            Organism organism = iterator.next();
            if (this.hunt(organism)){continue;}
            double organismCurrentWeight = organism.getCurrentWeight();
            if (neededFood >= organismCurrentWeight) {
                eatenFood += organismCurrentWeight;
                neededFood -= organismCurrentWeight;
                currentEat += organismCurrentWeight;
                iterator.remove();
            } else {
                eatenFood += neededFood;
                currentEat += neededFood;
                iterator.remove();
                neededFood -= neededFood;
            }
        }
        return currentEat;
    }
}
