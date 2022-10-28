package com.javarush.island.shubchynskyi.entity;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.plants.Plant;
import com.javarush.island.shubchynskyi.exception.IslandException;
import com.javarush.island.shubchynskyi.settings.EatingChance;
import com.javarush.island.shubchynskyi.utils.Generator;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.*;

import java.io.*;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArraySet;

public class EntityFactory {
    private EntityFactory() {}

    private static final Set<Animal> animalPrototypes = new HashSet<>();
    private static final Set<Plant> plantPrototypes = new HashSet<>();

    public static Set<Animal> getAnimalPrototypes() {
        return animalPrototypes;
    }
    public static Set<Plant> getPlantPrototypes() {
        return plantPrototypes;
    }

    static {
        initPrototypes();
    }

    private static void initPrototypes() {
        fillPrototypes(animalPrototypes, ANIMAL_PACKAGES);
        fillPrototypes(plantPrototypes, PLANT_PACKAGES);

        for (Animal prototype : animalPrototypes) {
            prototype.setChancesToEat(EatingChance.fillAnimalChanceToEat(prototype));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void fillPrototypes(Set<T> prototypeSet, String... packageWithEntityClasses) {
        for (String arg : packageWithEntityClasses) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(arg.replaceAll("[.]", "/"));
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                List<String> collect = reader.lines().map(s -> (s.substring(0, s.length() - 6))).toList();
                for (String s : collect) {
                    try {
                        prototypeSet.add((T) Class.forName(arg + "." + s).getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException | ClassNotFoundException e) {
                        throw new IslandException(e);
                    }
                }
            }
        }
    }

    public static Cell getFilledCell(int x, int y) {
        Cell resultCell = new Cell(x, y);

        for (Animal prototype : getAnimalPrototypes()) {
            randomAnimalFill(resultCell, prototype);
        }

        for (Plant prototype : getPlantPrototypes()) {
            randomPlantFill(resultCell, prototype);
        }

        return resultCell;
    }

    private static void randomAnimalFill(Cell resultCell, Animal prototype) {
        resultCell.getAnimalsInCell().put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
        int randomInt = Generator.getRandomForSpawn(0, prototype.getMaxPerCell());
        for (int i = 0; i < randomInt; i++) {
            resultCell.getAnimalsInCell().get(prototype.getAvatar()).add(prototype.clone(resultCell));
        }
    }

    private static void randomPlantFill(Cell resultCell, Plant prototype) {
        resultCell.getPlantsInCell().put(prototype.getAvatar(), new CopyOnWriteArraySet<>());
        int randomInt = Generator.getRandomForSpawn(0, prototype.getMaxPerCell());
        for (int i = 0; i < randomInt; i++) {
            resultCell.getPlantsInCell().get(prototype.getAvatar()).add(prototype.clone(resultCell));
        }
    }

}
