package com.javarush.island.marzhiievskyi.wiev;

import com.javarush.island.marzhiievskyi.entity.field.Cell;
import com.javarush.island.marzhiievskyi.entity.field.GameField;
import com.javarush.island.marzhiievskyi.entity.organisms.Organism;
import com.javarush.island.marzhiievskyi.utils.Constants;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleGUI {


    public void printField(GameField gameField) {
        System.out.println();
        Cell[][] fieldIsland = gameField.getFieldIsland();
        for (Cell[] cells : fieldIsland) {
            for (Cell cell : cells) {

                StringBuilder sb = new StringBuilder("[");
                Map<Organism, Set<Organism>> mapOfAnimalsOPnCell = cell.getMapOfAnimalsOnCell();
                mapOfAnimalsOPnCell.forEach((key, value) -> {
                    sb.append(" ").append(key.toString()).append(": ").append(new DecimalFormat("000").format(value.size()));
                });
                sb.append(" ]   ");
                System.out.print(sb);

            }
            System.out.println();
        }
    }

    public void printStatistic(GameField gameField) {
        AtomicInteger totalOrganisms = new AtomicInteger();
        Cell[][] fieldIsland = gameField.getFieldIsland();
        for (Cell[] cells : fieldIsland) {
            for (Cell cell : cells) {
                Map<Organism, Set<Organism>> mapOfAnimalsOnCell = cell.getMapOfAnimalsOnCell();
                mapOfAnimalsOnCell.forEach((k, v) -> {
                    int size = v.size();
                    totalOrganisms.set(totalOrganisms.get() + size);
                });
            }
        }
        System.out.println(Constants.TOTAL_ORGANISMS + totalOrganisms.get());
    }

}
