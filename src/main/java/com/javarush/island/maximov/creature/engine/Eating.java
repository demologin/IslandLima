package com.javarush.island.maximov.creature.engine;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.utils.Randomizer;
import com.javarush.island.maximov.world.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface Eating {
    default void startEat(Creature creature) {
        FamilyIdSettings familyIdSettings = creature.getFamilyIdSettings();
        if (!familyIdSettings.getEatProbabilityList().isEmpty()) {
            Tile currentTile = creature.getTile();
            FamilyIdSettings foodFamilyIdSettings = selectFamilyForFood(familyIdSettings, currentTile);
            if (foodFamilyIdSettings != null && checkEatProbability(familyIdSettings, foodFamilyIdSettings)) {
                Creature animalForFood = selectAnimalForFood(currentTile, foodFamilyIdSettings);
                if (animalForFood != null) {
                    eatAnimal(creature, familyIdSettings, animalForFood, currentTile);
                }
            }
        }
    }

    default boolean checkEatProbability(FamilyIdSettings familyIdSettings, FamilyIdSettings foodFamilyIdSettings) {
        return (Randomizer.randomIntFromToNotInclude(1, 101)
                <= familyIdSettings.getEatProbabilityList().get(foodFamilyIdSettings));
    }

    default void eatAnimal(Creature creature, FamilyIdSettings familyIdSettings, Creature foodAnimal,
                           Tile currentTile) {
        foodAnimal.die();
        currentTile.decrementIndexListOfAnimals(familyIdSettings.getFamilyIdNumber());
        double familyIdSettingsMaxWeight = familyIdSettings.getMaxWeight();
        double weightFoodForEat = Math.min(foodAnimal.getCurrentWeight(), familyIdSettingsMaxWeight);
        double totalFoodForPredator = Math.min(creature.getCurrentWeight() + weightFoodForEat,
                familyIdSettingsMaxWeight);
        creature.setCurrentWeight(totalFoodForPredator);
    }

    default Creature selectAnimalForFood(Tile currentTile, FamilyIdSettings foodFamilyIdSettings) {
        int familyIdNumber = foodFamilyIdSettings.getFamilyIdNumber();
        int indexForListOfAnimal = currentTile.getIndexListOfAnimals().get(familyIdNumber).get();
        if (indexForListOfAnimal > -1) {
            Creature animalForFood = currentTile.getListOfAnimals().get(familyIdNumber).get(indexForListOfAnimal);
            if (!animalForFood.isLive() || animalForFood.getTile().getIndex() != currentTile.getIndex()) {
                currentTile.decrementIndexListOfAnimals(familyIdNumber);
                return selectAnimalForFood(currentTile, foodFamilyIdSettings);
            }
            return animalForFood;
        }
        return null;
    }

    default FamilyIdSettings selectFamilyForFood(FamilyIdSettings familyIdSettings, Tile tile) {
        List<FamilyIdSettings> foodAvailableList = new ArrayList<>();
        for (Map.Entry<FamilyIdSettings, AtomicInteger> tileTypeAnimal : tile.getNumbersOfAnimals().entrySet()) {
            if (familyIdSettings.getEatProbabilityList().containsKey(tileTypeAnimal.getKey())
                    && tileTypeAnimal.getValue().get() > 0) {
                foodAvailableList.add(tileTypeAnimal.getKey());
            }
        }
        if (!foodAvailableList.isEmpty()) {
            return foodAvailableList.get(Randomizer.randomIntFromToNotInclude(0, foodAvailableList.size()));
        }
        return null;
    }
}
