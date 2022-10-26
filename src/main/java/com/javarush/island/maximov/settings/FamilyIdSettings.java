package com.javarush.island.maximov.settings;

import com.javarush.island.maximov.world.World;

import java.util.HashMap;
import java.util.Map;

public class FamilyIdSettings {
    private final World world;
    private final String name;
    private final String legend;
    private final String symbol;
    private final int familyIdNumber;
    private final double maxWeight;
    private final double weightLoss;
    private final int maxInTitle;
    private final int speed;
    private final FoodChain foodChain;
    private final boolean isShowOnMap;

    private final Map<FamilyIdSettings, Integer> eatProbabilityList = new HashMap<>();

    public FamilyIdSettings(int familyIdNumber, World world, String name, String legend, String symbol,
                            double maxWeight, int maxInTitle, int speed, FoodChain foodChain, boolean isShowOnMap,
                            double weightLoss) {
        this.familyIdNumber = familyIdNumber;
        this.world = world;
        this.name = name;
        this.legend = legend;
        this.symbol = symbol;
        this.maxWeight = maxWeight;
        this.maxInTitle = maxInTitle;
        this.speed = speed;
        this.foodChain = foodChain;
        this.isShowOnMap = isShowOnMap;
        this.weightLoss = weightLoss;
    }

    public String getName() {
        return name;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getMaxInTitle() {
        return maxInTitle;
    }

    public int getSpeed() {
        return speed;
    }

    public Map<FamilyIdSettings, Integer> getEatProbabilityList() {
        return eatProbabilityList;
    }

    public String getLegend() {
        return legend;
    }

    public String getSymbol() {
        return symbol;
    }

    public FoodChain getModeOfNutrition() {
        return foodChain;
    }

    public boolean isShowOnMap() {
        return isShowOnMap;
    }

    public World getWorld() {
        return world;
    }

    public int getFamilyIdNumber() {
        return familyIdNumber;
    }

    public double getWeightLoss() {
        return weightLoss;
    }

    public void addElementToEatProbabilityList(FamilyIdSettings familyIdSettings, Integer probability) {
        eatProbabilityList.put(familyIdSettings, probability);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamilyIdSettings that = (FamilyIdSettings) o;

        return familyIdNumber == that.familyIdNumber;
    }

    @Override
    public int hashCode() {
        return familyIdNumber;
    }
}
