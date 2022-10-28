package com.javarush.island.alimin.entity.ecosystem.animals;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.entity.location.Location;
import com.javarush.island.alimin.properties.OrganismParameters;
import com.javarush.island.alimin.properties.Properties;
import com.javarush.island.alimin.utils.Random;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Animal extends Organism {

    public Animal(OrganismParameters parameters) {
        super(parameters);
    }

    public abstract boolean eat(Cell cell);

    public void move(Cell startCell) {
        int maxSpeed = this.getParameters().getMaxSpeed();
        int deltaRow = Random.random(0, maxSpeed + 1);
        int deltaColumn = Random.random(0, maxSpeed + 1);
        boolean canMove = checkDelta(deltaRow, deltaColumn);

        if (canMove) {
            int newRow = getNewRow(startCell.getRow(), deltaRow);
            int newColumn = getNewColumn(startCell.getColumn(), deltaColumn);
            Types animalType = Types.valueOf(this.getClass().getSimpleName().toUpperCase());
            Cell destinationCell = Location.getInstance().getLocation()[newRow][newColumn];
            boolean isAnimalAdded = addAnimal(destinationCell, animalType);
            if (isAnimalAdded) {
                startCell.getLock().lock();
                try {
                    startCell.getResidents().get(animalType).remove(this);
                } finally {
                    startCell.getLock().unlock();
                }
            }
        }
    }

    private boolean checkDelta(int deltaRow, int deltaColumn) {
        if (deltaRow == 0 && deltaColumn == 0) {
            return false;
        }
        return deltaRow <= 0 || deltaColumn <= 0 || deltaRow == deltaColumn;
    }

    private int getNewRow(int currentRow, int deltaRow) {
        if (currentRow + deltaRow >= Properties.LOCATION_SIZE_ROW) {
            return currentRow - deltaRow;
        }
        return currentRow + deltaRow;
    }

    private int getNewColumn(int currentColumn, int deltaColumn) {
        if (currentColumn + deltaColumn >= Properties.LOCATION_SIZE_COLUMN) {
            return currentColumn - deltaColumn;
        }
        return currentColumn + deltaColumn;
    }

    private boolean addAnimal(Cell destinationCell, Types animalType) {
        destinationCell.getLock().lock();
        try {
            boolean result = true;
            Map<Types, Set<Organism>> residents = destinationCell.getResidents();
            if (residents.containsKey(animalType)) {
                Set<Organism> organisms = residents.get(animalType);
                if (organisms.size() < this.getParameters().getMaxCount()) {
                    organisms.add(this);
                } else {
                    result = false;
                }
            } else {
                Set<Organism> organisms = new HashSet<>();
                organisms.add(this);
                residents.put(animalType, organisms);
            }
            return result;
        } finally {
            destinationCell.getLock().unlock();
        }
    }

    public void loseWeight() {
            double weightLoss = (this.getCurrentWeight() * Properties.LOSE_WEIGHT_PERCENT) / 100;
            this.setCurrentWeight(this.getCurrentWeight() - weightLoss);
    }

    public boolean hunt(Organism organism) {
        Types hunterType = Types.valueOf(this.getClass().getSimpleName().toUpperCase());
        Types victimType = Types.valueOf(organism.getClass().getSimpleName().toUpperCase());
        if (Properties.eatProbability.get(hunterType).get(victimType) == null) {
            return true;
        }
        int eatProbabilityPercent = Properties.eatProbability.get(hunterType).get(victimType);
        return !Random.getProbability(eatProbabilityPercent);
    }

    @Override
    public boolean isDead() {
        return this.getCurrentWeight() < this.getParameters().getMaxWeight() / Properties.LIFE_THREATENING_WEIGHT_LOSS_INDEX;
    }
}
