package com.javarush.island.khmelov.abstraction.entity;

import com.javarush.island.khmelov.entity.map.Cell;

@SuppressWarnings("unused")
@FunctionalInterface
public interface Eating {

    boolean eat(Cell currentCell);

}
