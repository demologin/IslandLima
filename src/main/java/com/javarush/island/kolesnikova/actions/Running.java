package com.javarush.island.kolesnikova.actions;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.field.Cell;
import com.javarush.island.kolesnikova.entities.field.GameField;
import com.javarush.island.kolesnikova.entities.units.Animals;
import com.javarush.island.kolesnikova.entities.units.Unit;
import com.javarush.island.kolesnikova.factory.UnitsFactory;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.island.kolesnikova.utils.Utils.getRandom;

public interface Running {

    static void run() {
        Cell[][] field = GameField.getField();
        for (int y = 0; y < GameField.getRowY(); y++) {
            for (int x = 0; x < GameField.getColX(); x++) {
                Cell cell = field[y][x];
                for (PropertiesUnit.UnitsName name : cell.getUnitsInCell().keySet()) {
                    Set<Unit> units = cell.getSetUnitsInCell(name);
                    HashSet<Unit> unitsCopy = new HashSet<>(units);
                    if (UnitsFactory.getUnit(name) instanceof Animals) {
                        for (Unit nextUnit : unitsCopy) {
                            int maxUnitsInCell = nextUnit.getMaxUnitsInCell();
                            int[] one = new int[]{-1, 1};
                            int takeOne = getRandom(1);
                            int newX = cell.getX() + (getRandom(nextUnit.getSpeed()) * one[takeOne]);
                            int newY = cell.getY() + (getRandom(nextUnit.getSpeed()) * one[takeOne]);

                            if (newX < 0) {
                                newX *= -1;
                            }
                            if (newY < 0) {
                                newY *= -1;
                            }
                            boolean isMove = true;
//                            System.out.printf("%s %d|%d � %d|%d = ", units.iterator().next().getName(), cell.getX(), cell.getY(), newX, newY);

                            if (newX >= GameField.getColX() || newY >= GameField.getRowY() || newX < 0 || newY < 0) {
//                                System.out.println("FALSE | ����� �� ������� ����");
                            } else if (newX == cell.getX() && newY == cell.getY()) {
//                                System.out.println("FALSE | �� �� ������");
                            } else {
                                Cell cellTarget = GameField.getField()[newY][newX];
                                Set<Unit> setUnitsInCellTarget = cellTarget.getSetUnitsInCell(name);
                                int numberOfOneTypeOfUnitsInNewCell = setUnitsInCellTarget.size();
                                if (numberOfOneTypeOfUnitsInNewCell >= maxUnitsInCell) {
//                                    System.out.println("FALSE | �������������");
                                    isMove = false;
                                }
                                if (isMove) {
//                                    System.out.println("TRUE | �������");
                                    setUnitsInCellTarget.add(nextUnit.clone());
                                    units.remove(nextUnit);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
