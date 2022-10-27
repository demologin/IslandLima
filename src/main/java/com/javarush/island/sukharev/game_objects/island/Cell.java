package com.javarush.island.sukharev.game_objects.island;

import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;

import java.util.Set;

public class Cell {
    int fieldCoordinateX;
    int fieldCoordinateY;
    Set<Animal> animalSet;
    Set<Vegetation>  grassSet;

    public Cell(int fieldCoordinateX, int fieldCoordinateY, Set<Animal> animalSet, Set<Vegetation> grassSet) {
        this.fieldCoordinateX = fieldCoordinateX;
        this.fieldCoordinateY = fieldCoordinateY;
        this.animalSet = animalSet;
        this.grassSet = grassSet;
    }

    public int getFieldCoordinateX() {
        return fieldCoordinateX;
    }

    public int getFieldCoordinateY() {
        return fieldCoordinateY;
    }

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public void setAnimalSet(Set<Animal> animalSet) {
        this.animalSet = animalSet;
    }

    public Set<Vegetation> getGrassSet() {
        return grassSet;
    }

    public void setGrassSet(Set<Vegetation> grassSet) {
        this.grassSet = grassSet;
    }

    @Override

    public String toString() {
        return "Cell " + "X = " + fieldCoordinateX +
                ", Y = " + fieldCoordinateY +
                ", A L = " + animalSet +
                ", G L = " + grassSet +
                '}';
    }
}
