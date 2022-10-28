package com.javarush.island.ivanilov.behaviours;

import com.javarush.island.ivanilov.creatures.Creature;
import com.javarush.island.ivanilov.game.Cell;

public interface Eating {
    void eat(Creature creature, Cell cell);
}
