package com.javarush.island.maximov.creature.engine;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.settings.FamilyIdSettings;

public interface Mortal {
    default void startDie(Creature creature) {
        if (isHaveWeight(creature)) {
            FamilyIdSettings familyIdSettings = creature.getFamilyIdSettings();
            lowerWeight(creature, familyIdSettings);
        }
    }

    default void lowerWeight(Creature creature, FamilyIdSettings familyIdSettings) {
        double weight = creature.getCurrentWeight() - familyIdSettings.getWeightLoss();
        creature.setCurrentWeight(weight);
        if (weight <= 0) {
            creature.die();
        }
    }

    default boolean isHaveWeight(Creature creature) {
        return creature.getCurrentWeight() > 0;
    }
}
