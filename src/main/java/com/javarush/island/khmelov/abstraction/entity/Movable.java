package com.javarush.island.khmelov.abstraction.entity;

import com.javarush.island.khmelov.entity.map.Cell;

@FunctionalInterface
public interface Movable {

    @FunctionalInterface
    interface Finder {
        int getRating(Cell nextCell);
    }

    Cell move(Cell startCell);

}
