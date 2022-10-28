package com.javarush.island.khlopin.field;

import com.javarush.island.khlopin.units.Unit;

import java.util.List;
import java.util.StringJoiner;

public class MapOfIsland {

    private final int position = 2;
    private final String border = "═".repeat(position);



    public String drawMap() {
        Cell[][] cells = GameField.field;
        final int cols = GameField.getCols();
        final int rows = GameField.getRows();
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < rows; i++) {
            stringBuilder.append(i == 0
                    ? line(cols,'╔', '╦', '╗')
                    : line(cols, '╠', '╬', '╣')
            ).append("\n");
            for (int i1 = 0; i1 < cols; i1++) {
                String residentString = get(cells[i][i1]);
                stringBuilder.append(String.format("║%-" + position + "s", residentString));
            }
            stringBuilder.append("                              ║").append("\n");
        }
        stringBuilder.append(line(cols,'╚', '╩', '╝'));
        return stringBuilder.toString();
    }

    private String get(Cell cell) {
        String icon = null;
        for (java.util.Map.Entry<String, List<Unit>> animalListEntry : cell.sets.entrySet()) {
            for (Unit unit : animalListEntry.getValue()) {
                icon = unit.getProperties().icon;
            }
        }
        return icon;
    }

    private String line(int cols, char left, char center, char right) {
        StringJoiner joiner = new StringJoiner("", "", String.valueOf(right));
        for (int col = 0; col < cols; col++) {
            String s = (col == 0 ? left : center) + border;
            joiner.add(s);
        }
        return (joiner.toString());
    }


}
