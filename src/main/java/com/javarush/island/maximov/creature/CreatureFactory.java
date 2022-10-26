package com.javarush.island.maximov.creature;

import com.javarush.island.maximov.creature.animals.*;
import com.javarush.island.maximov.exception.CreatureFactoryException;
import com.javarush.island.maximov.settings.FamilyIdSettings;

public class CreatureFactory {
    private CreatureFactory() {
    }

    public static Creature createCreature(long id, String name,
                                          double maxWeight, FamilyIdSettings familyIdSettings) {
        int familyIdNumber = familyIdSettings.getFamilyIdNumber();
        return switch (familyIdNumber) {
            case 1 -> new Wolf(id, name, familyIdSettings, maxWeight);
            case 2 -> new Anaconda(id, name, familyIdSettings, maxWeight);
            case 3 -> new Fox(id, name, familyIdSettings, maxWeight);
            case 4 -> new Bear(id, name, familyIdSettings, maxWeight);
            case 5 -> new Eagle(id, name, familyIdSettings, maxWeight);
            case 6 -> new Horse(id, name, familyIdSettings, maxWeight);
            case 7 -> new Deer(id, name, familyIdSettings, maxWeight);
            case 8 -> new Rabbit(id, name, familyIdSettings, maxWeight);
            case 9 -> new Mouse(id, name, familyIdSettings, maxWeight);
            case 10 -> new Goat(id, name, familyIdSettings, maxWeight);
            case 11 -> new Sheep(id, name, familyIdSettings, maxWeight);
            case 12 -> new Hog(id, name, familyIdSettings, maxWeight);
            case 13 -> new Buffalo(id, name, familyIdSettings, maxWeight);
            case 14 -> new Duck(id, name, familyIdSettings, maxWeight);
            case 15 -> new Caterpillar(id, name, familyIdSettings, maxWeight);
            case 16 -> new Plants(id, name, familyIdSettings, maxWeight);
            default -> throw new CreatureFactoryException("FamilyID [" + familyIdNumber + "] not found.");
        };
    }
}
