package com.javarush.island.sternard.actions.interfaces;

import com.javarush.island.sternard.map.Cell;
import com.javarush.island.sternard.organisms.parents.Animal;

@SuppressWarnings({"UnusedDeclaration"})
public interface Eating {
    void action(Animal animal, Cell cell);
}
