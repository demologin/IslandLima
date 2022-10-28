package com.javarush.island.marzhiievskyi.entity.organisms.actions;

import com.javarush.island.marzhiievskyi.entity.field.Cell;

import java.io.IOException;

public interface Eatable {

    void eat(Cell cell) throws IOException;
}
