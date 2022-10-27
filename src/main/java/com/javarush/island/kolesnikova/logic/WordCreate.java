package com.javarush.island.kolesnikova.logic;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.field.Cell;
import com.javarush.island.kolesnikova.entities.field.GameField;
import com.javarush.island.kolesnikova.entities.units.Unit;
import com.javarush.island.kolesnikova.factory.UnitsFactory;
import com.javarush.island.kolesnikova.utils.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class WordCreate {

    public static void create() {

        for (int y = 0; y < GameField.getRowY(); y++) {
            for (int x = 0; x < GameField.getColX(); x++) {
                HashMap<PropertiesUnit.UnitsName, Set<Unit>> unitsInCell = new HashMap<>();
                Cell cell = new Cell(x, y, unitsInCell);
                Cell[][] field = GameField.getField();
                field[y][x] = cell;
                for (PropertiesUnit.UnitsName name : PropertiesUnit.allUnits()) {
                    Unit unit = UnitsFactory.getUnit(name);
                    int maxUnitsInCell = unit.getMaxUnitsInCell();
                    int numRandom = Utils.getRandom(maxUnitsInCell);
                    Set<Unit> unitsOneTypeSet = new HashSet<>();
                    for (int n = 0; n < numRandom; n++) {
                        unitsOneTypeSet.add(unit.clone());
                    }
                    unitsInCell.put(name, unitsOneTypeSet);
                }

            }

        }
    }


}