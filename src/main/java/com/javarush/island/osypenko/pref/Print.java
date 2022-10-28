package com.javarush.island.osypenko.pref;

import com.javarush.island.osypenko.constants.CharacteristicsEntities;
import com.javarush.island.osypenko.entities.Organism;
import com.javarush.island.osypenko.exception.AnimalException;
import com.javarush.island.osypenko.field.Cell;
import com.javarush.island.osypenko.field.GameField;

import java.util.Map;
import java.util.Set;

import static com.javarush.island.osypenko.constants.Constants.REPEAT;
import static com.javarush.island.osypenko.field.GameField.field;

public class Print {
    boolean isLife = true;
    public void runner() {
        GameField gameField = new GameField();
        gameField.initialize();
        System.out.println("*".repeat(REPEAT) + "\n");
        printState();
        System.out.println("*".repeat(REPEAT) + "\n");

        do {
            gameField.makeStep();
            try {
                //noinspection BusyWait
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new AnimalException(e);
            }
            printState();
            System.out.println("*".repeat(REPEAT) + "\n");
        } while (isLife);
    }
    public void printState() {
        int bear = 0;
        int eagle = 0;
        int fox = 0;
        int snake = 0;
        int wolf = 0;
        int boar = 0;
        int buffalo = 0;
        int caterpillar = 0;
        int deer = 0;
        int duck = 0;
        int goat = 0;
        int horse = 0;
        int mouse = 0;
        int rabbit = 0;
        int sheep = 0;
        int grass = 0;


        for (Cell[] cells : field) {
            for (Cell item : cells) {
                Map<EntitiesType, Set<Organism>> res = item.sets;
                for (Set<Organism> value : res.values()) {
                    for (Organism organism : value) {
                        switch (organism.getClass().getSimpleName().toUpperCase()) {
                            case "BEAR" -> bear++;
                            case "EAGLE" -> eagle++;
                            case "FOX" -> fox++;
                            case "SNAKE" -> snake++;
                            case "WOLF" -> wolf++;
                            case "BOAR" -> boar++;
                            case "BUFFALO" -> buffalo++;
                            case "CATERPILLAR" -> caterpillar++;
                            case "DEER" -> deer++;
                            case "DUCK" -> duck++;
                            case "GOAT" -> goat++;
                            case "HORSE" -> horse++;
                            case "MOUSE" -> mouse++;
                            case "RABBIT" -> rabbit++;
                            case "SHEEP" -> sheep++;
                            case "GRASS" -> grass++;
                        }
                    }
                }
            }
        }

        System.out.println(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BEAR).getIcon() + " = " + printColor(bear) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.EAGLE).getIcon() + " = " + printColor(eagle) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.FOX).getIcon() + " = " + printColor(fox) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.SNAKE).getIcon() + " = " + printColor(snake) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.WOLF).getIcon() + " = " + printColor(wolf) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BOAR).getIcon() + " = " + printColor(boar) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BUFFALO).getIcon() + " = " + printColor(buffalo) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.CATERPILLAR).getIcon() + " = " + printColor(caterpillar) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.DEER).getIcon() + " = " + printColor(deer) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.DUCK).getIcon() + " = " + printColor(duck) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.GOAT).getIcon() + " = " + printColor(goat) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.HORSE).getIcon() + " = " + printColor(horse) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.MOUSE).getIcon() + " = " + printColor(mouse) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.RABBIT).getIcon() + " = " + printColor(rabbit) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.SHEEP).getIcon() + " = " + printColor(sheep) + "  " +
                CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.GRASS).getIcon() + " = " + printColor(grass) + "  \n");

        if (bear == 0 && eagle == 0 && fox == 0 && snake == 0 && wolf == 0 && boar == 0 && buffalo == 0 && caterpillar == 0 &&
                deer == 0 && duck == 0 && goat == 0 && horse == 0 && mouse == 0 && rabbit == 0 && sheep == 0 && grass == 0) {
            isLife = false;
        }
    }

    private static String printColor(int i) {
        String str;
        if (i == 0) {
            str = "\033[0;31m" + i + "\033[0m";
        } else {
            str = "\033[0;32m" + i + "\033[0m";
        }
        return str;
    }
}

