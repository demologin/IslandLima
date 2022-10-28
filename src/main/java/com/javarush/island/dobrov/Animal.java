package com.javarush.island.dobrov;

import com.javarush.island.dobrov.Field.Cell;
import com.javarush.island.dobrov.Field.Field;
import com.javarush.island.dobrov.exceptions.IslandException;
import com.javarush.island.dobrov.utils.AnimalType;
import com.javarush.island.dobrov.utils.Animal_Properties;
import com.javarush.island.dobrov.utils.RandomGenerator;
import com.javarush.island.dobrov.utils.Settings;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal implements Cloneable {


    public AnimalType returnType() {
        return null;
    }

    public void eat(Cell cell, Animal animal, double hunger, double weight) {

    }

    public void move(Cell cell, Animal animal, int speed) {
        Class<? extends Animal> aClass = animal.getClass();
        Animal_Properties annotation = aClass.getAnnotation(Animal_Properties.class);
        int maxAreaPopulation = annotation.maxAreaPopulation();

        int oldCellRow = cell.getRow();
        int oldCellCol = cell.getCol();
        if (speed != 0) {
            int newCellRow = cell.getRow() + ThreadLocalRandom.current().nextInt(0, speed);
            int newCellCol = cell.getCol() + ThreadLocalRandom.current().nextInt(0, speed);


            if (!(newCellRow >= Settings.row || newCellCol >= Settings.col ||
                    (newCellRow == oldCellRow && newCellCol == oldCellCol))) {

                cell.getLock().lock();

                Cell cell1 = Field.field[oldCellRow][oldCellCol];
                Cell cell2 = Field.field[newCellRow][newCellCol];
                Set<Animal> oldCellSet = cell1.getAnimals().get(animal.getClass().getSimpleName());
                Set<Animal> newCellSet = cell2.getAnimals().get(animal.getClass().getSimpleName());
                if (newCellSet.size() < maxAreaPopulation)

                    newCellSet.add(animal);


                oldCellSet.remove(animal);

                cell.getLock().unlock();
            }
        }



    }

    public void multiply(Cell cell, Animal animal) {
        cell.getLock().lock();
        int generate = new RandomGenerator(100).generate();

        int number = 50;

        Set<Animal> animals1 = cell.getAnimals().get(animal.getClass().getSimpleName());

        if (animals1.size() > 2 && generate > number) {

            cell.getAnimals().get(animal.getClass().getSimpleName()).add(this);

        }
       cell.getLock().unlock();

    }

    public void dead() {
        System.out.println("dead");
    }


    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IslandException(e);
        }
    }


}
