package com.javarush.island.plugin.entity;

import com.javarush.island.plugin.gameset.Cell;
import com.javarush.island.plugin.gameset.Fields;


public class Plant extends Animal {
    public Plant(Fields fields) {
        super(fields);
    }

    @Override
    public boolean move(Cell cell) {
        return false;
    }

}
