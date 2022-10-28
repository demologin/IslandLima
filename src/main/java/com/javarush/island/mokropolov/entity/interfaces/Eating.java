package com.javarush.island.mokropolov.entity.interfaces;

import com.javarush.island.mokropolov.game.map.Cell;

@FunctionalInterface
public interface Eating {

    boolean eat(Cell currentCell);
}
