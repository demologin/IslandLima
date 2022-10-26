package com.javarush.island.maximov.creature.engine;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.GlobalSettings;
import com.javarush.island.maximov.utils.Randomizer;
import com.javarush.island.maximov.world.Tile;

public interface Reproducing {
    default void startReproduction(Creature creature) {
        FamilyIdSettings familyIdSettings = creature.getFamilyIdSettings();
        Tile tile = creature.getTile();
        GlobalSettings globalSettings = creature.getFamilyIdSettings().getWorld().getGlobalSettings();
        if (checkHungryStatus(creature, familyIdSettings)
                && isSameFamilyInTile(familyIdSettings, tile)
                && isNewTileAvailableWithCapacityReproduction(familyIdSettings, tile)
                && checkReproductionChange(globalSettings)) {
            reproduction(creature, familyIdSettings, tile);
        }
    }

    default boolean checkHungryStatus(Creature creature, FamilyIdSettings familyIdSettings) {
        return (creature.getCurrentWeight() * 100 / familyIdSettings.getMaxWeight()) > 50;
    }

    default boolean checkReproductionChange(GlobalSettings globalSettings) {
        return Randomizer.randomIntFromToNotInclude(0, 100) < globalSettings.getReproductionChance();
    }

    default boolean isNewTileAvailableWithCapacityReproduction(FamilyIdSettings familyIdSettings, Tile tile) {
        return tile.getNumbersOfAnimals().get(familyIdSettings).get() < familyIdSettings.getMaxInTitle();
    }

    default boolean isSameFamilyInTile(FamilyIdSettings familyIdSettings, Tile tile) {
        return tile.getNumbersOfAnimals().get(familyIdSettings).get() > 1;
    }

    default void reproduction(Creature creature, FamilyIdSettings familyIdSettings, Tile tile) {
        familyIdSettings.getWorld().createCreature(familyIdSettings, tile);
        changeWeightAfterReproduction(creature);
    }

    default void changeWeightAfterReproduction(Creature creature) {
        creature.setCurrentWeight(creature.getCurrentWeight() / 2);
    }
}
