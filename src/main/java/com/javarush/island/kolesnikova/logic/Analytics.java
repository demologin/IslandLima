package com.javarush.island.kolesnikova.logic;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.field.Cell;
import com.javarush.island.kolesnikova.entities.field.GameField;
import com.javarush.island.kolesnikova.entities.units.Unit;
import com.javarush.island.kolesnikova.factory.UnitsFactory;

import java.util.HashMap;
import java.util.Set;


public class Analytics {


    public static int getCellsStat() {

        HashMap<PropertiesUnit.UnitsName, Integer> mapStatistic = new HashMap<>(); // в мапу собираем статистику
        int ok = 0;
        Cell[][] field = GameField.getField();
        for (int y = 0; y < GameField.getRowY(); y++) {
            for (int x = 0; x < GameField.getColX(); x++) {
                Cell cell = field[y][x];

                for (PropertiesUnit.UnitsName name : cell.getUnitsInCell().keySet()) {
                    Set<Unit> setUnitsInCell = cell.getSetUnitsInCell(name);
                    int size = setUnitsInCell.size();
                    if (mapStatistic.containsKey(name) && mapStatistic.get(name) > 0) {
                        int i = mapStatistic.get(name) + (Integer) size;
                        mapStatistic.put(name, i);
                    } else {
                        mapStatistic.put(name, size);
                    }

                }
            }
        }
        for (PropertiesUnit.UnitsName name : mapStatistic.keySet()) {
            if (mapStatistic.get(name) > 0) {
                System.out.printf("%s %s = %d  ",
                        UnitsFactory.getUnit(name).getIcon(), UnitsFactory.getUnit(name).getName(), mapStatistic.get(name));
            }
            ok = ok + mapStatistic.get(name);
       }
        System.out.printf("\nВсего: " + ok);
        System.out.println();
        return  ok;
    }

}




