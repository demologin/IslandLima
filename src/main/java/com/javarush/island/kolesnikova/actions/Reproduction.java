package com.javarush.island.kolesnikova.actions;


import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.field.Cell;
import com.javarush.island.kolesnikova.entities.field.GameField;
import com.javarush.island.kolesnikova.entities.units.Animals;
import com.javarush.island.kolesnikova.entities.units.Unit;
import com.javarush.island.kolesnikova.factory.UnitsFactory;

import java.util.Set;

public interface Reproduction {



    static void multiply() {
        Cell[][] field = GameField.getField();
        for (int y = 0; y < GameField.getRowY(); y++) {
            for (int x = 0; x < GameField.getColX(); x++) {
                Cell cell = field[y][x];
                for (PropertiesUnit.UnitsName name : cell.getUnitsInCell().keySet()) {
                    int maxUnitsInCell = UnitsFactory.getUnit(name).getMaxUnitsInCell();
                    Set<Unit> units = cell.getSetUnitsInCell(name);
                    int numberOfOneTypeOfUnits = units.size();

                    if (UnitsFactory.getUnit(name) instanceof Animals) {
                        int numberOfNewUnitsMax = numberOfOneTypeOfUnits / 2;
                        int numberOfNewUnitsResult;
                        if (maxUnitsInCell >= (numberOfNewUnitsMax + numberOfOneTypeOfUnits)) {
                            numberOfNewUnitsResult = numberOfNewUnitsMax;
                        } else {
                            numberOfNewUnitsResult = maxUnitsInCell - numberOfOneTypeOfUnits;
                        }
                        for (int i = 0; i < numberOfNewUnitsResult; i++) {
                            units.add(UnitsFactory.getUnit(name).clone());
                        }
//                        System.out.printf("� %d|%d - %d %s. ������� %d. ����� %d/%d\n",
//                                cell.getX(), cell.getY(), numberOfOneTypeOfUnits, name, numberOfNewUnitsResult,
//                                cell.getUnitsInCell().get(name).size(), maxUnitsInCell);
                    } else {
                        int numberOfNewUnitsResult = maxUnitsInCell - numberOfOneTypeOfUnits;
                        for (int i = 0; i < numberOfNewUnitsResult; i++) {
                            units.add(UnitsFactory.getUnit(name).clone());
                        }
//                        System.out.printf("� %d|%d - %d %s. ������� %d. ����� %d/%d\n",
//                                cell.getX(), cell.getY(), numberOfOneTypeOfUnits, name, numberOfNewUnitsResult,
//                                cell.getUnitsInCell().get(name).size(), maxUnitsInCell);
                    }
                }
            }
        }
    }
}






