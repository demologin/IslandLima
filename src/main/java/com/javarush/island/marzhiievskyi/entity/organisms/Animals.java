package com.javarush.island.marzhiievskyi.entity.organisms;


import com.javarush.island.marzhiievskyi.entity.field.Cell;
import com.javarush.island.marzhiievskyi.entity.organisms.actions.Eatable;
import com.javarush.island.marzhiievskyi.entity.organisms.actions.Movable;
import com.javarush.island.marzhiievskyi.utils.Constants;
import com.javarush.island.marzhiievskyi.utils.ParametersForEating;
import com.javarush.island.marzhiievskyi.utils.gettingParameters.GettingParametersOfEating;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Animals extends Organism implements Eatable, Movable {
    private final String name;
    private final String icon;
    private final double maxWeight;
    private final int maxCountOnCell;
    private final int maxSpeed;
    private final double needFood;
    private double currentWeight;

    private final Organism currentType;


    public Animals(String name, String icon, double maxWeight, int maxCountOnCell, int maxSpeed, double needFood) {
        this.name = name;
        this.icon = icon;
        this.maxWeight = maxWeight;
        this.maxCountOnCell = maxCountOnCell;
        this.maxSpeed = maxSpeed;
        this.needFood = needFood;
        this.currentWeight = maxWeight;
        this.currentType = this;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getMaxCountOnCell() {
        return maxCountOnCell;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getNeedFood() {
        return needFood;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }


    @Override
    public void eat(Cell cell) {

        if (isNotDead(cell)) {
            lockedWeightLose(cell);

            List<GettingParametersOfEating.AnimalsEatable> eatParameters = LockedGetAnimalsEatables(cell);

            int magicRandomToEat = ThreadLocalRandom.current().nextInt(0, 100);                         // chance to eat
            int magicRandomWhatAnimalToEat = ThreadLocalRandom.current().nextInt(0, eatParameters.size());     // selecting type of organism to eat

            if (this instanceof HerbivorousAnimals) {
                magicRandomWhatAnimalToEat = eatParameters.indexOf(eatParameters.get(eatParameters.size() - 1));
            }

            String name = eatParameters.get(magicRandomWhatAnimalToEat).getName();

            if (magicRandomToEat < eatParameters.get(magicRandomWhatAnimalToEat).getChanceToEat()
                    && lockedCheckThatAnimalIsInCell(cell, name)
                    && this.currentWeight < this.maxWeight) {
                lockedEating(cell, name);
            }
        } else {
            lockedRemove(cell);
        }
    }

    private List<GettingParametersOfEating.AnimalsEatable> LockedGetAnimalsEatables(Cell cell) {
        cell.getLock().lock();
        try {
            return ParametersForEating.getParametersForEating().getEatParameters(this);
        } finally {
            cell.getLock().unlock();
        }

    }

    private void lockedEating(Cell cell, String name) {
        cell.getLock().lock();
        try {
            cell.getMapOfAnimalsOnCell().forEach((key, value) -> {
                if (name.equalsIgnoreCase(key.getClass().getSimpleName())) {
                    Organism organism = value.iterator().next();
                    if (organism instanceof Animals animals) {
                        this.setCurrentWeight(Math.min((animals.getCurrentWeight() + this.getCurrentWeight()), this.getMaxWeight()));
                        animals.setCurrentWeight(0);
                    } else if (organism instanceof Plants plants) {
                        this.setCurrentWeight(Math.min((plants.getCurrentWeight() + this.getCurrentWeight()), this.getMaxWeight()));
                        plants.setCurrentWeight(0);
                    }
                }
            });
        } finally {
            cell.getLock().unlock();
        }
    }

    private boolean lockedCheckThatAnimalIsInCell(Cell cell, String name) {
        cell.getLock().lock();
        try {
            AtomicBoolean result = new AtomicBoolean(false);
            Map<Organism, Set<Organism>> mapOfAnimalsOnCell = cell.getMapOfAnimalsOnCell();
            mapOfAnimalsOnCell.forEach((key, value) -> {
                if (key.getClass().getSimpleName().toLowerCase().equals(name)) {
                    if (value.size() != 0) {
                        result.set(true);

                    }
                }
            });
            return result.get();
        } finally {
            cell.getLock().unlock();
        }


    }

    @Override
    public void move(Cell cell) {

        if (isNotDead(cell)) {
            lockedWeightLose(cell);
            if (ThreadLocalRandom.current().nextBoolean() && this.maxSpeed != 0) {
                lockedMoveOnOneCell(cell);
            }
        } else {
            lockedRemove(cell);
        }
    }

    private void lockedMoveOnOneCell(Cell cell) {
        int speed = this.maxSpeed;
        Cell destinationCell = lockedGetTargetCell(cell);
        while (speed > 1) {

            destinationCell = lockedGetTargetCell(destinationCell);
            speed--;


        }
        int destinationCellCountAnimals = destinationCell.getMapOfAnimalsOnCell().get(currentType).size();

        if (destinationCellCountAnimals < this.maxCountOnCell) {
            lockedAddToDestCell(destinationCell);
            lockedRemove(cell);
        }


    }

    private void lockedAddToDestCell(Cell destinationCell) {
        destinationCell.getLock().lock();
        try {
            destinationCell.getMapOfAnimalsOnCell().get(currentType).add(this.clone());

        } finally {
            destinationCell.getLock().unlock();
        }
    }

    private Cell lockedGetTargetCell(Cell cell) {
        cell.getLock().lock();
        try {
            List<Cell> roadToMove = cell.generateMoveList(cell);
            int chosenRoad = ThreadLocalRandom.current().nextInt(0, roadToMove.size());
            return roadToMove.get(chosenRoad);
        } finally {
            cell.getLock().unlock();
        }


    }

    private void lockedWeightLose(Cell cell) {
        cell.getLock().lock();
        try {
            this.currentWeight = this.currentWeight - (this.currentWeight * Constants.WEIGHT_LOSE_PER_ACTION) / 100;
        } finally {
            cell.getLock().unlock();
        }
    }

    @Override
    public void multiply(Cell cell) {

        if (isNotDead(cell)) {
            lockedWeightLose(cell);
            lockedMultiply(cell);
        } else {
            lockedRemove(cell);
        }
    }

    private void lockedMultiply(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Organism> organismSet = cell.getMapOfAnimalsOnCell().get(currentType);
            if (organismSet.size() > 1) {
                int chanceMultiply = ThreadLocalRandom.current().nextInt(0, 100);
                if (chanceMultiply < Constants.CHANCE_TO_BIRTH_CHILD) {

                    for (int i = 0; i < Constants.COUNT_OF_DESCENDANTS_FOR_ANIMALS; i++) {
                        if (organismSet.size() < this.maxCountOnCell) {
                            organismSet.add(this.clone());
                        }
                    }
                }
            }
        } finally {
            cell.getLock().unlock();
        }


    }

    private void lockedRemove(Cell cell) {

        cell.getLock().lock();

        try {
            cell.getMapOfAnimalsOnCell().get(currentType).remove(this);

        } finally {
            cell.getLock().unlock();
        }
    }

    public boolean isNotDead(Cell cell) {
        cell.getLock().lock();
        try {
            return (!(this.currentWeight < (this.maxWeight - this.needFood)));
        } finally {
            cell.getLock().unlock();
        }
    }

    @Override
    public String toString() {
        return this.icon;
    }
}
