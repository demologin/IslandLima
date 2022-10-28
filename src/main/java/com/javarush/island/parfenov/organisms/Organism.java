package com.javarush.island.parfenov.organisms;

import com.javarush.island.parfenov.gameMechanics.Cell;
import com.javarush.island.parfenov.gameMechanics.Phase;
import com.javarush.island.parfenov.gameMechanics.Vault;
import com.javarush.island.parfenov.settings.Characteristics;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public abstract class Organism implements Cloneable {
    public static AtomicLong idGenerator = new AtomicLong(0L);
    public static AtomicInteger amountOfAnimals = new AtomicInteger(0);
    private long id;
    private final AtomicReference<Phase> phase = new AtomicReference<>(Phase.FREE);
    private boolean alive = true;
    private Map<String, Integer> chances;
    private Double weight;
    private final Double maxWeight;
    private final Double percent;
    private final Integer cellLimit;
    private final Integer maxCellByMove;
    private Integer movingPoints;
    private final String nameOfOrganism = this.getClass().getSimpleName();
    private final Double feedPortion;
    private Cell nextDestination = null;
    private final Integer pointsOfEating;
    private final Map<Characteristics, Number> characteristics;

    public Organism(Map<Characteristics, Number> characteristics, Map<String, Integer> chances) {
        this.characteristics = characteristics;
        this.id = idGenerator.incrementAndGet();
        this.weight = (Double) characteristics.get(Characteristics.WEIGHT);
        percent = weight / 100 * 5;
        maxWeight = weight * 2;
        this.cellLimit = (Integer) characteristics.get(Characteristics.CELL_LIMIT);
        this.maxCellByMove = (Integer) characteristics.get(Characteristics.MAX_CELL_BY_MOVE);
        movingPoints = maxCellByMove;
        this.feedPortion = (Double) characteristics.get(Characteristics.FEED_PORTION);
        this.chances = chances;
        this.pointsOfEating = (Integer) characteristics.get(Characteristics.POINTS_OF_EATING);
    }

    public void multiply(Cell cell) {
        boolean multiplyOrNot = ThreadLocalRandom.current().nextBoolean();
        if (multiplyOrNot &&
            cell.getCertainResidents(nameOfOrganism).size() + cell.getCertainMigrants(nameOfOrganism).size() < cellLimit) {
            cell.setAmountOfMigrants(nameOfOrganism, cell.getAmountOfMigrants(nameOfOrganism) + 1);
            cell.getCertainMigrants(nameOfOrganism).add(this.clone());
            this.weight /= 2;
        }
    }

    public void move(Cell srcCell) {
        boolean moveOrNot = ThreadLocalRandom.current().nextBoolean();
        if (moveOrNot && isAlive() && movingPoints > 0) {
            Cell destCell = findNextCell(srcCell);
            if (destCell != null && destCell != srcCell) {
                destCell.setAmountOfMigrants(nameOfOrganism, destCell.getAmountOfMigrants(nameOfOrganism) + 1);
                destCell.getCertainMigrants(nameOfOrganism).add(this);
                nextDestination = destCell;
            }
            movingPoints = maxCellByMove;
        }
    }

    private Cell findNextCell(Cell srcCell) {
        if (movingPoints > 0) {
            List<Cell> cells = srcCell.getCellsNearby();
            List<Integer> order = new ArrayList<>(IntStream.rangeClosed(0, cells.size() - 1).boxed().toList());
            Collections.shuffle(order);
            for (Integer indexOfCell : order) {
                Cell destCell = cells.get(indexOfCell);
                Set<Organism> organisms = destCell.getCertainResidents(nameOfOrganism);
                if (organisms.size() + destCell.getAmountOfMigrants(nameOfOrganism) < cellLimit) {
                    movingPoints--;
                    Cell res = findNextCell(destCell);
                    return Objects.requireNonNullElse(res, destCell);
                }
            }
        }
        return null;
    }

    public void changeWeightByTime() {
        weight -= percent;
        if (weight <= 0) {
            this.alive = false;
        }
    }

    public void eat(Cell cell) {//TODO check if no points
        int points = pointsOfEating;
        if (weight < maxWeight / 2) {
            boolean unexpectedDeath = false;
            for (Map.Entry<String, Vault> entry : cell.getPersons().entrySet()) {//TODO change to more random (or more smart) searching of victim
                if (unexpectedDeath || (pointsOfEating == 0) || phase.get() == Phase.BUSY || weight > maxWeight)
                    break;//TODO change points compare to <= (make less brittle)
                String name = entry.getKey();
                if (cell.getCertainResidents(name).isEmpty()) continue;
                Integer chance = chances.get(name);
                if (chance != null && chance > 0) {
                    Set<Organism> residents = entry.getValue().getResidents();
                    for (int i = 0; i < pointsOfEating && points > 0; i++) {
                        Organism victim = residents.stream().skip(ThreadLocalRandom.current().nextInt(0, residents.size())).findFirst().orElse(null);
//                    }
                        if (unexpectedDeath || phase.get() == Phase.BUSY || weight > maxWeight) {
                            break;
                        }
                        if (victim.isAlive() && victim.phase.compareAndSet(Phase.FREE, Phase.BUSY)) {
                            points--;
                            if (phase.compareAndSet(Phase.FREE, Phase.BUSY)) {
                                if (isAlive()) {
                                    if (victim.isAlive()) {
                                        int res = ThreadLocalRandom.current().nextInt(1, 101);
                                        if (res <= chance) {
                                            double atePortion = Math.min(Math.min(victim.getWeight(), this.feedPortion), maxWeight - weight);
                                            victim.setWeight(victim.getWeight() - atePortion);
                                            if (victim.getWeight() <= 0) {
                                                victim.setAlive(false);
                                            }
                                            victim.phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                            this.weight += atePortion;
                                            phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                        } else {
                                            victim.phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                            phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                        }
                                    } else {
                                        victim.phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                        phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                        break;
                                    }
                                } else {
                                    victim.phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                    phase.compareAndSet(Phase.BUSY, Phase.FREE);
                                    unexpectedDeath = true;
                                    break;
                                }
                            } else {
                                victim.phase.compareAndSet(Phase.BUSY, Phase.FREE);
                            }
                        }
                    }

                }
            }
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getCellLimit() {
        return cellLimit;
    }

    @Override
    public String toString() {
        return "Organism{" +
                alive
                +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }

    @Override
    public Organism clone() {
        try {
            Organism clone = (Organism) super.clone();
            clone.setId(idGenerator.incrementAndGet());
            clone.setWeight(this.weight);//TODO CHANGE THE WEIGHT!!!
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Map<String, Integer> getChances() {
        return chances;
    }

    public void setChances(Map<String, Integer> chances) {
        this.chances = chances;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Cell getNextDestination() {
        return nextDestination;
    }

    public String getNameOfOrganism() {
        return nameOfOrganism;
    }

    public Map<Characteristics, Number> getCharacteristics() {
        return characteristics;
    }
}
