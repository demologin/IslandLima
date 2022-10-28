package com.javarush.island.alimin.properties;

import java.util.Objects;
@SuppressWarnings("unused")
public class OrganismParameters {
    private final String name;
    private final String icon;
    private final double maxWeight;
    private final int maxCount;
    private final int maxSpeed;
    private final double maxFood;

    public OrganismParameters(String name, String icon, double MaxWeight, int maxCount, int maxSpeed, double maxFood) {
        this.name = name;
        this.icon = icon;
        this.maxWeight = MaxWeight;
        this.maxCount = maxCount;
        this.maxSpeed = maxSpeed;
        this.maxFood = maxFood;
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

    public int getMaxCount() {
        return maxCount;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxFood() {
        return maxFood;
    }


    @Override
    public String toString() {
        return "Organism{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", maxWeight=" + maxWeight +
                ", maxCount=" + maxCount +
                ", maxSpeed=" + maxSpeed +
                ", maxFood=" + maxFood +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganismParameters that = (OrganismParameters) o;
        return Double.compare(that.maxWeight, maxWeight) == 0 && maxCount == that.maxCount && maxSpeed == that.maxSpeed && Double.compare(that.maxFood, maxFood) == 0 && Objects.equals(name, that.name) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, icon, maxWeight, maxCount, maxSpeed, maxFood);
    }
}
