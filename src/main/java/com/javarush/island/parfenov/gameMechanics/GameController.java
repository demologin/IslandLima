package com.javarush.island.parfenov.gameMechanics;

import com.javarush.island.parfenov.organisms.Organism;
import com.javarush.island.parfenov.settings.Characteristics;
import com.javarush.island.parfenov.settings.GameSettings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GameController {
    private final Map<String, Organism> prototypes = new HashMap<>();
    private final GameSettings gameSettings;
    private Cell[][] field;

    public GameController(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        prototypesInit(gameSettings);
        prepareField();
        fillField();
        addNeighbourCells();
        System.out.println(Organism.amountOfAnimals.get());
    }

    private void addNeighbourCells() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {

                int[][] arr = {
                        {x - 1, y - 1},
                        {x, y - 1},
                        {x + 1, y - 1},
                        {x + 1, y},
                        {x + 1, y + 1},
                        {x, y + 1},
                        {x - 1, y + 1},
                        {x - 1, y},
                };
                for (int[] coordinates : arr) {
                    if ((coordinates[0] >= 0 && coordinates[0] < field[y].length) &&
                            (coordinates[1] >= 0 && coordinates[1] < field.length)) {
                        field[y][x].getCellsNearby().add(field[coordinates[1]][coordinates[0]]);
                    }
                }
            }
        }
    }

    private void prototypesInit(GameSettings gameSettings) {
        for (Class<?> aClass : gameSettings.getOrganismClasses()) {
            try {
                Constructor<?> constructor = aClass.getConstructor(Map.class, Map.class);
                Map<Characteristics, Number> standardCharacteristics = gameSettings.getStandardCharacteristics().get(aClass.getSimpleName());
                HashMap<String, Integer> chances = gameSettings.getChances().get(aClass.getSimpleName());
                prototypes.put(aClass.getSimpleName(), (Organism) constructor.newInstance(standardCharacteristics, chances));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void prepareField() {
        field = new Cell[gameSettings.getRealFieldSizes().getHeight()][gameSettings.getRealFieldSizes().getWidth()];
    }

    private void fillField() {
        Random random = new Random();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell();
                for (Organism organism : prototypes.values()) {
                    String name = organism.getClass().getSimpleName();
                    field[i][j].getPersons().put(name, new Vault());
                    int randomAmountOfOrganisms = random.nextInt(0, organism.getCellLimit() + 1);
                    Set<Organism> setOfCertainTypeOfOrganisms = field[i][j].getCertainResidents(name);
                    for (int k = 0; k < randomAmountOfOrganisms; k++) {
                        setOfCertainTypeOfOrganisms.add(organism.clone());
                        Organism.amountOfAnimals.incrementAndGet();
                    }
                }
            }
        }
    }

    public Cell[][] getField() {
        return field;
    }

    public Map<String, Organism> getPrototypes() {
        return prototypes;
    }
}
