package com.javarush.island.osypenko.field;

import com.javarush.island.osypenko.constants.CharacteristicsEntities;
import com.javarush.island.osypenko.constants.Constants;
import com.javarush.island.osypenko.entities.Organism;
import com.javarush.island.osypenko.pref.EntitiesType;
import com.javarush.island.osypenko.pref.FactoryOrganism;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {
    public static Cell[][] field = new Cell[Constants.CELL_X][Constants.CELL_Y];

    public void initialize() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                EntitiesType[] entitiesTypes = EntitiesType.values();
                Cell cell = new Cell(i, j);
                for (EntitiesType entitiesType : entitiesTypes) {
                    int num = ThreadLocalRandom.current().nextInt(0, CharacteristicsEntities.getCharacteristicsEntities().get(entitiesType).getMaxNumberOfAnimalsOfThisSpeciesPerCage());
                    Set<Organism> typeSet = new HashSet<>();

                    for (int k = 0; k < num; k++) {
                        Organism organism = FactoryOrganism.getOrganism(entitiesType);
                        typeSet.add(organism);
                        cell.sets.put(entitiesType, typeSet);
                        field[i][j] = cell;
                    }
                }
            }
        }
    }

    public void makeStep() {
        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                cell.makeStep();
            }
        }
    }
}
