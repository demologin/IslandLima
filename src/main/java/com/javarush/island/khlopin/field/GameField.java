package com.javarush.island.khlopin.field;

import com.javarush.island.khlopin.settings.Preferences;
import com.javarush.island.khlopin.units.Unit;
import com.javarush.island.khlopin.units.UnitDistributor;
import com.javarush.island.khlopin.units.UnitFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {
    public static Cell[][] field = new Cell[Preferences.Y][Preferences.X];
    public UnitDistributor unitDistributor = new UnitDistributor();



    // Заселить поле животными и растениями
    public void initialize()  {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Cell cell = new Cell(i, j);
                for (Unit unit : unitDistributor.getUnits()) {
                    int num = ThreadLocalRandom.current().nextInt(0, unit.getProperties().maxCount);
                    List<Unit> unitSet = new ArrayList<>();
                    for (int i1 = 0; i1 < num; i1++) {
                        unitSet.add(UnitFactory.bornUnitByName(unit.getClass().getSimpleName()));
                    }
                    cell.sets.put(unit.getClass().getSimpleName(), unitSet);
                    field[i][j] = cell;
                }
            }
        }
    }

    // Сделать шаг (пробижаться по всем ячейкам)
    public void makeStep()  {
        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                cell.makeStep();
            }
        }
    }

    // Вывести статистику


        public static int getCols () {
            return field[0].length;
        }

        public static int getRows () {
            return field.length;
        }

}