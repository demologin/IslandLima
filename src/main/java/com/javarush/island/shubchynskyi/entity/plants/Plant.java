package com.javarush.island.shubchynskyi.entity.plants;

import com.javarush.island.shubchynskyi.entity.animals.Organism;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.settings.EntitySettings.EntityEnums;
import com.javarush.island.shubchynskyi.utils.FieldCreator;
import com.javarush.island.shubchynskyi.utils.Generator;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.shubchynskyi.entity.EntityFactory.getPlantPrototypes;
import static com.javarush.island.shubchynskyi.settings.Constants.*;
import static com.javarush.island.shubchynskyi.settings.GameSettings.PLANT_GROW_AND_SPREADING_CHANCE;
import static com.javarush.island.shubchynskyi.settings.GameSettings.PLANT_PERCENT_SPAWN_CHANCE;

public abstract class Plant implements Organism, Cloneable {

    private static final AtomicInteger plantCount = new AtomicInteger(0);

    private String name;
    private final EntityEnums type;
    private double weight;
    private final int maxPerCell;
    private final String avatar;

    public double getMaxWeight() {
        return maxWeight;
    }

    private final double maxWeight;
    private boolean isAlive = true;
    private Cell currentCell;

    public Plant() {
        this.name = (String) FieldCreator.getField(this, NAME);
        this.type = (EntityEnums) FieldCreator.getField(this, TYPE);
        this.weight = (double) FieldCreator.getField(this, WEIGHT);
        this.maxPerCell = (int) FieldCreator.getField(this, MAX_PER_CELL);
        this.avatar = (String) FieldCreator.getField(this, AVATAR);
        maxWeight = weight;
    }

    @Override
    public void startLife() {
        spawn();
    }

    public void decreaseWeight(double weight) {
        this.weight = this.weight - weight;
    }

    public void increaseWeight(double weight) {
        this.weight = this.weight - weight;
    }

    /**
     * A plant with a low weight produces offspring with the same weight.
     * There is a chance of plant growth and a chance of spreading to neighboring empty cells.
     */
    public void spawn() {
        getCurrentCell().getLock().lock();
        try {
            if (Generator.checkChance(PLANT_PERCENT_SPAWN_CHANCE)) {
                int toSpawn = getMaxPerCell() - getCurrentCell().getPlantsInCell().get(getAvatar()).size();
                if (toSpawn > 0) {
                    for (Plant plantPrototype : getPlantPrototypes()) {
                        if (plantPrototype.getAvatar().equals(getAvatar())) {
                            toSpawn = Generator.getRandomForSpawn(0, toSpawn);
                            for (int i = 0; i < toSpawn; i++) {
                                getCurrentCell().getPlantsInCell().get(getAvatar()).add(clone(getCurrentCell()));
                            }
                            break;
                        }
                    }
                }
            }
            if (Generator.checkChance(PLANT_GROW_AND_SPREADING_CHANCE)) {
                growAndSpreading();
            }
        } finally {
            getCurrentCell().getLock().unlock();
        }
    }

    private void growAndSpreading() {
        for (Cell neighbour : getCurrentCell().getNeighbours()) {
            if (neighbour.getPlantsInCell().get(getAvatar()).size() == 0) {
                neighbour.getPlantsInCell().get(getAvatar()).add(clone(neighbour));
            }
        }
        if (getWeight() < getMaxWeight()) {
            increaseWeight((getMaxWeight() - getWeight()) / 5);
        }
    }

    public void die(Cell cell) {
        cell.getLock().lock();
        try {
            getCurrentCell().getPlantsInCell().get(getAvatar()).remove(this);
            setAlive(false);
        } finally {
            cell.getLock().unlock();
        }
    }

    public Plant clone(Cell cell) {
        try {
            Plant result = (Plant) super.clone();
            result.name = result.name + " " + plantCount.incrementAndGet();
            result.setCurrentCell(cell);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public String getName() {
        return name;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Double.compare(plant.weight, weight) == 0 && maxPerCell == plant.maxPerCell && isAlive == plant.isAlive && Objects.equals(name, plant.name) && type == plant.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
