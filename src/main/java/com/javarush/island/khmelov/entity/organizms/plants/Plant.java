package com.javarush.island.khmelov.entity.organizms.plants;

import com.javarush.island.khmelov.abstraction.annotations.TypeData;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizms.Limit;
import com.javarush.island.khmelov.entity.organizms.Organism;

import java.util.Set;

@TypeData(name = "Трава", icon = "\uD83E\uDEB4", maxWeight = 1, maxCount = 200, maxSpeed = 0, maxFood = 0)
public class Plant extends Organism {
    public Plant(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean spawn(Cell cell) {
        Cell neighborCell = cell.getNextCell(1);
        return safePlantPropagation(neighborCell);
    }

    private boolean safePlantPropagation(Cell cell) {
        cell.getLock().lock();
        try {
            this.safeChangeWeight(cell, 75);
            Set<Organism> plants = cell.getResidents().get(getType());
            if (plants.size() < getLimit().getMaxCount() &&
                    getWeight() > getLimit().getMaxWeight() / 20
            ) {
                Organism newPlant = Organism.clone(this);
                double childWeight = getWeight()/5;
                newPlant.setWeight(childWeight);
                return plants.add(newPlant);
            }
        } finally {
            cell.getLock().unlock();
        }
        return false;
    }
}
