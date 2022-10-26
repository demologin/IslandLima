package com.javarush.island.ivanilov.game;

import com.javarush.island.ivanilov.builders.AnimalBuilder;
import com.javarush.island.ivanilov.builders.CellBuilder;
import com.javarush.island.ivanilov.creatures.Animal;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Getter
public class GameField {
    static int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final Cell[][] realm;
    private final AnimalBuilder animalBuilder;
    private final int rows;
    private final int columns;

    public GameField(int rows, int columns){
        this.animalBuilder = new AnimalBuilder();
        this.realm = generateGameField(rows, columns);
        this.rows = rows;
        this.columns = columns;
    }

    public Cell[][] generateGameField(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IslandGameException("Game field has no rows or columns");

        Cell[][] realm = new Cell[rows][columns];
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        CellBuilder cellBuilder = new CellBuilder(this, this.animalBuilder);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    Future<Cell> cell = executor.submit(cellBuilder);
                    realm[i][j] = cell.get();
                    realm[i][j].setRow(i);
                    realm[i][j].setColumn(j);
                } catch (Exception e) {
                    throw new IslandGameException(e.getCause());
                }
            }
        }
        executor.shutdown();
        return realm;
    }

    public String printField() {
        return renderTable(realm);
    }

    private String renderTable(Cell[][] realm) {
        if (columns > 0 && columns <= 20) {
            String[][] strings = new String[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Cell cell = realm[i][j];
                    Map.Entry<Type, Set<Animal>> animalEntry = mostPopularAnimal(cell);
                    StringBuilder stringBuilder = new StringBuilder();

                    if (animalEntry == null) {
                        stringBuilder.append("-");
                    } else {
                        Type animal = animalEntry.getKey();
                        int quantity = animalEntry.getValue().size();
                        Map<Type, Object> prototypes = animalBuilder.prototypes;
                        Animal prototype = (Animal) prototypes.get(animal);
                        if (columns > 10) {
                            stringBuilder.append(prototype.getIcon());
                        } else {
                            stringBuilder.append(prototype.getIcon())
                                    .append(quantity);
                        }
                    }

                    stringBuilder.append("\\");

                    if (cell.getPlants().getMass() == 0) {
                        stringBuilder.append("-");
                    } else {
                        if (columns > 10) {
                            stringBuilder.append(cell.getPlants().getIcon());
                        } else {
                            stringBuilder.append(cell.getPlants().getIcon())
                                    .append((Long) Math.round(cell.getPlants().getMass()));
                        }
                    }
                    strings[i][j] = stringBuilder.toString();
                }
            }

            Table table;
            if (columns <= 10) {
                table = Table.of(strings, Alignment.LEFT, 14);
            } else {
                table = Table.of(strings, Alignment.LEFT, 6);
            }
            return table.toString();
        }

        if (columns > 20) {
            return "Number of columns exceeds 20: com.javarush.ivanilov.game field render is not possible."; //TODO extract variable
        }
        return "Default string";
    }

    private Map.Entry<Type, Set<Animal>> mostPopularAnimal(Cell cell) {
        Optional<Map.Entry<Type, Set<Animal>>> animalOrNull = cell.getAnimals()
                .entrySet()
                .stream()
                .filter(o -> o.getValue().size() != 0)
                .min((o1, o2) -> Long.compare(o2.getValue().size(), o1.getValue().size()));

        return animalOrNull.orElse(null);
    }

    @Override
    public String toString() {
        return "GameField{" +
                "rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
