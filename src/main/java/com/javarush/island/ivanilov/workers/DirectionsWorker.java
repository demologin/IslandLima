package com.javarush.island.ivanilov.workers;

import com.javarush.island.ivanilov.entities.Direction;
import com.javarush.island.ivanilov.game.Cell;
import com.javarush.island.ivanilov.game.GameField;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DirectionsWorker implements Runnable {
    private final Cell cell;
    private final GameField gameField;

    public DirectionsWorker(Cell cell, GameField gameField) {
        this.cell = cell;
        this.gameField = gameField;
    }

    @Override
    public void run() {
        Map<Direction, Cell> surroundings = determineSurroundings(cell, gameField);
        synchronized (cell) {
            cell.setSurroundings(surroundings);
        }
    }

    private Map<Direction, Cell> determineSurroundings(Cell cell, GameField gameField) {
        this.cell.getLock().lock();
        Map<Direction, Cell> directionCellMap = new ConcurrentHashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            Direction direction = Direction.values()[i];
            Optional<Cell> cellOptional = searchTarget(direction, gameField, cell.getRow(), cell.getColumn());
            if (cellOptional.isPresent()) {
                Cell target = cellOptional.get();
                directionCellMap.put(direction, target);
            }
        }
        this.cell.getLock().unlock();
        return directionCellMap;
    }

    private Optional<Cell> searchTarget(Direction direction, GameField gameField, int row, int column) {
        Cell[][] realm = gameField.getRealm();

        return switch (direction) {
            case NORTH -> {
                try {
                    yield Optional.of(realm[row - 1][column]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case NORTH_EAST -> {
                try {
                    yield Optional.of(realm[row - 1][column + 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case EAST -> {
                try {
                    yield Optional.of(realm[row][column + 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case SOUTH_EAST -> {
                try {
                    yield Optional.of(realm[row + 1][column + 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case SOUTH -> {
                try {
                    yield Optional.of(realm[row + 1][column]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case SOUTH_WEST -> {
                try {
                    yield Optional.of(realm[row + 1][column - 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case WEST -> {
                try {
                    yield Optional.of(realm[row][column - 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
            case NORTH_WEST -> {
                try {
                    yield Optional.of(realm[row - 1][column - 1]);
                } catch (Exception e) {
                    yield Optional.empty();
                }
            }
        };
    }
}
