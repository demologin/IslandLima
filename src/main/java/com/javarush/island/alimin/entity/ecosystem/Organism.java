package com.javarush.island.alimin.entity.ecosystem;

import com.javarush.island.alimin.entity.location.Cell;
import com.javarush.island.alimin.properties.OrganismParameters;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Organism {

    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    private final long id = idCounter.incrementAndGet();

    private final OrganismParameters parameters;

    private double currentWeight;

    private boolean reproduceStatus = false;


    public Organism(OrganismParameters parameters) {
        this.parameters = parameters;
        currentWeight = parameters.getMaxWeight();
    }

    public abstract boolean isDead();

    public boolean reproduce(Cell cell) {
        boolean result = false;
        Types animalType = Types.valueOf(this.getClass().getSimpleName().toUpperCase());
        Set<Organism> organisms = cell.getResidents().get(animalType);
        if (!this.reproduceStatus && organisms.contains(this)
                && organisms.size() >= 2
                && organisms.size() < parameters.getMaxCount()
                && this.currentWeight == this.parameters.getMaxWeight()) {
            this.reproduceStatus = true;
            result = true;
        }
        return result;
    }


    public OrganismParameters getParameters() {
        return parameters;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return id == organism.id && reproduceStatus == organism.reproduceStatus && Objects.equals(parameters, organism.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parameters, reproduceStatus);
    }

    @Override
    public String toString() {
        return parameters.toString();
    }
}


