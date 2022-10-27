package com.javarush.island.dobrov.Field;

import com.javarush.island.dobrov.Animal;
import com.javarush.island.dobrov.Grass.Grass;
import com.javarush.island.dobrov.exceptions.IslandException;
import com.javarush.island.dobrov.herbivores.*;
import com.javarush.island.dobrov.predators.*;
import com.javarush.island.dobrov.utils.Animal_Properties;
import com.javarush.island.dobrov.utils.RandomGenerator;
import com.javarush.island.dobrov.utils.Settings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Field {



    public static Cell[][] field = new Cell[Settings.row][Settings.col];

    private final List<Class<? extends Animal>> animalClasses = Arrays.asList(Buffalo.class,
            Bunny.class, Caterpillar.class, Deer.class, Duck.class, Goat.class,
            Hog.class, Horse.class, Mouse.class, Sheep.class, Bear.class, Eagle.class,
            Fox.class, Snake.class, Wolf.class, Grass.class);

    private final List<String> list;




    public Field() {
        list = new ArrayList<>();
        list.add(Buffalo.class.getSimpleName());
        list.add(Bunny.class.getSimpleName());
        list.add(Caterpillar.class.getSimpleName());
        list.add(Deer.class.getSimpleName());
        list.add(Duck.class.getSimpleName());
        list.add(Goat.class.getSimpleName());
        list.add(Hog.class.getSimpleName());
        list.add(Horse.class.getSimpleName());
        list.add(Mouse.class.getSimpleName());
        list.add(Sheep.class.getSimpleName());
        list.add(Bear.class.getSimpleName());
        list.add(Eagle.class.getSimpleName());
        list.add(Fox.class.getSimpleName());
        list.add(Snake.class.getSimpleName());
        list.add(Wolf.class.getSimpleName());
        list.add(Grass.class.getSimpleName());


        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                Cell cell = new Cell(i, j);

                field[i][j] = cell;
                for (Class<? extends Animal> animalClass : animalClasses) {
                    Animal_Properties annotation = animalClass.getAnnotation(Animal_Properties.class);
                    if (animalClass.isAnnotationPresent(Animal_Properties.class)) {
                        try {
                            Constructor<? extends Animal> constructor = animalClass.getConstructor();
                            Animal animal = constructor.newInstance();
                            Class<? extends Animal> aClass = animal.getClass();

                            RandomGenerator randomGenerator = new RandomGenerator(annotation.maxAreaPopulation());
                            int generate = randomGenerator.generate();

                            Set<Animal> animalSet = new HashSet<>(generate);
                            for (int x = 0; x < generate; x++) {
                                animalSet.add((Animal) animal.clone());
                            }
                            cell.animals.put(aClass.getSimpleName(), animalSet);

                        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                                 NoSuchMethodException e) {
                            throw new IslandException(e);
                        }
                    }
                }

            }
        }
    }

    public static Cell[][] getField() {
        return field;
    }


    public void showStat() {
        Map<String, Set<Animal>> statistics = new HashMap<>();
        for (Class<? extends Animal> animalClass : animalClasses) {
            statistics.put(animalClass.getSimpleName(), new HashSet<>());
        }
        Cell[][] cells = Field.getField();
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                Map<String, Set<Animal>> animals = cell1.getAnimals();
                    for (String s1 : list) {
                        Set<Animal> animals1 = statistics.get(s1);
                        Set<Animal> animals2 = animals.get(s1);
                        animals1.addAll(animals2);

                    }
            }
        }
        for (Map.Entry<String, Set<Animal>> s : statistics.entrySet()) {
            System.out.print(s.getKey() + " " + s.getValue().size() + " ");
        }
        System.out.println();

    }


    @Override
    public String toString() {
        return "Field";
    }
}
