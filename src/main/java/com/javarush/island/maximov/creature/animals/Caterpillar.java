package com.javarush.island.maximov.creature.animals;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.creature.engine.Eating;
import com.javarush.island.maximov.creature.engine.Mortal;
import com.javarush.island.maximov.creature.engine.Reproducing;
import com.javarush.island.maximov.settings.FamilyIdSettings;

public class Caterpillar extends Creature implements Mortal, Reproducing, Eating {
    public Caterpillar(long id, String name, FamilyIdSettings familyId, double currentWeight) {
        super(id, name, familyId, currentWeight);
    }
}
