package com.javarush.island.marzhiievskyi.entity.organisms;


import com.javarush.island.marzhiievskyi.entity.field.Cell;
import com.javarush.island.marzhiievskyi.entity.organisms.actions.Reproductionable;

public abstract class Organism implements Cloneable, Reproductionable {

    @Override
    public Organism clone() {
        try {
            return (Organism) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void multiply(Cell cell) {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
