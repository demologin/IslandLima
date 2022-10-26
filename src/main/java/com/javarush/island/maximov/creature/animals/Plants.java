package com.javarush.island.maximov.creature.animals;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.creature.engine.Reproducing;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.GlobalSettings;
import com.javarush.island.maximov.world.Tile;

public class Plants extends Creature implements Reproducing {
    public Plants(long id, String name, FamilyIdSettings familyId, double currentWeight) {
        super(id, name, familyId, currentWeight);
    }

    @Override
    public boolean isSameFamilyInTile(FamilyIdSettings familyIdSettings, Tile tile) {
        return true;
    }

    @Override
    public boolean checkHungryStatus(Creature creature, FamilyIdSettings familyIdSettings) {
        return true;
    }

    @Override
    public boolean checkReproductionChange(GlobalSettings globalSettings) {
        return true;
    }
}
