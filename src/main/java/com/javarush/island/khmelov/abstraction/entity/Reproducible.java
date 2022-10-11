package com.javarush.island.khmelov.abstraction.entity;

import com.javarush.island.khmelov.entity.map.Cell;

@FunctionalInterface
public interface Reproducible {

    void spawn(Cell currentCell);

}
