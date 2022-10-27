package com.javarush.island.sukharev.factory;

import com.javarush.island.sukharev.animal_manipulator.ManagerGod;
import com.javarush.island.sukharev.storage.DataBase;
import com.javarush.island.sukharev.storage.ProgramCommunication;

import java.math.BigDecimal;
import java.util.Scanner;

public class Settings {



    public void setUpOrLeave(ProgramCommunication pc, boolean language) {
        ManagerGod god = ManagerGod.getManagerGod();
        DataBase dataBase = DataBase.getDataBase();
        System.out.printf("%s%n", pc.defaultSettings(language));

        if (flagRegulator(pc, language)) {
            settingsOptions(dataBase, god, pc, language);
        } else {
            defaultInitializationAllVariables(dataBase);
            god.start(settings, pc, language, dataBase);
        }
    }


    private void settingsOptions(DataBase dataBase, ManagerGod god, ProgramCommunication pc, boolean language) {
        int numberOfProcessedObjects = ProgramCommunication.OBJECT_NUMBER;

        System.out.printf("%s%n", ProgramCommunication.settingsGameObjects(language));
        boolean choice = flagRegulator(pc, language);

        if (choice) {
            parameterInitialization(dataBase, pc, language, numberOfProcessedObjects);
        } else {
            defaultInitializationRequiredVariable(dataBase, numberOfProcessedObjects);
        }

        if (flagPresenceOfObjects(numberOfProcessedObjects)) settingsOptions(dataBase, god, pc, language);

        god.start(settings, pc, language, dataBase);
    }

    private void parameterInitialization(DataBase dataBase, ProgramCommunication pc, boolean language, int objectNumber) {
        if (objectNumber == 0) {
            dataBase.setIslandLength(giveANumber
                    (pc, language, pc.variableNameIsland(0), 0, objectNumber).intValue());
            dataBase.setIslandWidth( giveANumber
                    (pc, language, pc.variableNameIsland(1), 1, objectNumber).intValue());
        } else {
            BigDecimal[] myBigDecimal = new BigDecimal[4];

            for (int i = 0; i < myBigDecimal.length; i++) {
                myBigDecimal[i] = giveANumber
                        (pc, language, pc.variableNameAnimal(i), i, objectNumber);
            }
            dataBase.getDoublesObject().add(myBigDecimal);
        }
    }

    private BigDecimal giveANumber
            (ProgramCommunication pc, boolean language, String appropriation, int variableNumber, int objectNumber) {
        double[][] doubles = animalParametersLimit(objectNumber);

        System.out.printf("Введите число больше %.2f и меньше %.2f%n",
                doubles[variableNumber][0], doubles[variableNumber][1]);
        System.out.print(appropriation);

        return letAnswer(pc, language, doubles[variableNumber][0], doubles[variableNumber][1]);
    }

    private void defaultInitializationRequiredVariable(DataBase dataBase, int numberOfProcessedObjects) {
        if ((numberOfProcessedObjects == 0)) {
            dataBase.islandDimensions();
        } else {
            dataBase.getDoublesObject().add(dataBase.animalParameters(numberOfProcessedObjects));
        }


    }

    private void defaultInitializationAllVariables(DataBase dataBase) {
        dataBase.islandDimensions();
        for (int i = 1; i < 16; i++) {
            dataBase.getDoublesObject().add(dataBase.animalParameters(i));
        }
    }

    private boolean flagPresenceOfObjects(int numberOfProcessedObjects) {
        int numberOfObjects = 16;
        int correctionFactorZero = 1;

        return numberOfProcessedObjects != numberOfObjects - correctionFactorZero;
    }

    private double[][] animalParametersLimit(int operationNumber) {
        return switch (operationNumber) {
            case 0 -> new double[][]{{1, 200}, {1, 200}};                                    //Поле
            case 1 -> new double[][]{{40, 80}, {0, 100}, {0, 10}, {3, 10}};                  //Волк
            case 2 -> new double[][]{{10, 20}, {0, 100}, {0, 10}, {1, 8}};                   //Удав
            case 3 -> new double[][]{{5, 15}, {0, 100}, {0, 10}, {1, 6}};                    //Лиса
            case 4 -> new double[][]{{300, 700}, {0, 15}, {0, 10}, {40, 100}};               //Медведь
            case 5 -> new double[][]{{3, 15}, {0, 100}, {0, 10}, {1, 4}};                    //Орел
            case 6 -> new double[][]{{150, 800}, {0, 100}, {0, 10}, {30, 80}};               //Лошадь
            case 7 -> new double[][]{{150, 500}, {0, 100}, {0, 10}, {20, 70}};               //Олень
            case 8 -> new double[][]{{1, 8}, {0, 100}, {0, 10}, {0.1, 0.5}};                 //Кролик
            case 9 -> new double[][]{{0.02, 0.1}, {0, 2000}, {0, 10}, {0.01, 0.08}};         //Мышь
            case 10 -> new double[][]{{30, 80}, {0, 500}, {0, 10}, {3, 15}};                 //Коза
            case 11 -> new double[][]{{30, 80}, {0, 500}, {0, 10}, {5, 25}};                 //Овца
            case 12 -> new double[][]{{150, 700}, {0, 200}, {0, 10}, {20, 100}};             //Кабан
            case 13 -> new double[][]{{150, 1000}, {0, 50}, {0, 10}, {30, 200}};             //Буйвол
            case 14 -> new double[][]{{1, 5}, {0, 1000}, {0, 10}, {0.01, 0.5}};              //Утка
            case 15 -> new double[][]{{0.01, 0.1}, {0, 5000}, {0, 10}, {0, 0.03}};           //Гусеница

            default -> throw new IllegalStateException("Unexpected value: " + operationNumber);
        };
    }

    /**
     * Sets the value of boolean language
     * who will be responsible for the use in the program,
     * either Russian or English
     * depending on the value of the variable
     */
    public boolean flagRegulator(ProgramCommunication pc, boolean language) {
        double upperLimit = 1;
        double lowerLimit = 2;
        int numberSettings = letAnswer(pc, language, upperLimit, lowerLimit).intValue();
        return numberSettings == 1;
    }

    /**
     * Strongly recommends the user to select one of the required integer value options
     */
    public BigDecimal letAnswer(ProgramCommunication pc, boolean language, double upperLimit, double lowerLimit) {
        Scanner scanner = new Scanner(System.in);
        String num;
        do {
            num = scanner.next();
        } while (!isValidNumber(num, pc, language, upperLimit, lowerLimit));
        return new BigDecimal("" + num);
    }

     private boolean isValidNumber
             (String x, ProgramCommunication pc, boolean language, double upperLimit, double lowerLimit) {
        try {
            boolean isValidNumber;
            double numberSettings = Double.parseDouble(x);
            if (upperLimit == 1 && lowerLimit == 2) {
                isValidNumber =  numberSettings == 1 || numberSettings == 2;
            } else {
                isValidNumber = numberSettings > (upperLimit - 0.01) && numberSettings < (lowerLimit + 0.01);
            }
            if (!isValidNumber) System.out.print(pc.phrasesForNumberVerificationMethod(language));
            return isValidNumber;
        } catch (NumberFormatException e) {
            System.out.print(pc.phrasesForNumberVerificationMethodException(language));
            return false;
        }
    }

    private static Settings settings;

    private Settings() {
    }

    public static synchronized Settings getSettings() {
        if (settings == null) {
            settings = new Settings();
            return settings;
        }
        return settings;
    }
}