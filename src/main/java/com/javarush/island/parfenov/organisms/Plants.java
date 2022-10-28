package com.javarush.island.parfenov.organisms;

import com.javarush.island.parfenov.gameMechanics.Cell;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.Map;

public abstract class Plants extends Organism{
    public Plants(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        super(characteristics, chances);
    }

    public void multiply(Cell cell) {
        if (cell.getCertainResidents(getNameOfOrganism()).size() + cell.getCertainMigrants(getNameOfOrganism()).size() < getCellLimit()) {
            String name = getNameOfOrganism();
            int amount = 10;
            while (amount-- > 0 && cell.getCertainResidents(name).size() + cell.getCertainMigrants(name).size() < getCellLimit()) {
                cell.setAmountOfMigrants(name, cell.getAmountOfMigrants(name) + 1);
                cell.getCertainMigrants(name).add(this.clone());
            }
        }
    }

    public void changeWeightByTime() {
        setWeight(getWeight() + getPercent());
        if (getWeight() <= 0) {
            setAlive(false);
        }
    }
}
