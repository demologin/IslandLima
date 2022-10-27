package com.javarush.island.sukharev.storage;

import com.javarush.island.sukharev.game_objects.animal.abstracts.Animal;
import com.javarush.island.sukharev.game_objects.animal.herbivores.*;
import com.javarush.island.sukharev.game_objects.animal.predatory.*;
import com.javarush.island.sukharev.game_objects.vegetation.abstracts.Vegetation;
import com.javarush.island.sukharev.game_objects.vegetation.plant.Grass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public int[] numberOfOffspring = {6, 50, 14, 4, 4, 2, 3, 12, 11, 5, 4, 10, 3, 21, 10};
    public String[] animals = {"Wolf", "Boa", "Fox", "Bear", "Eagle", "Horse", "Deep", "Rabbit", "Mouse", "Goat",
            "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar"};
    private static DataBase dataBase;
    public static final int ATTEMPTS = 1;
    public static final int ATTEMPTS_REPRODUCTION = 100;

    private List<BigDecimal[]> doublesObject = new ArrayList<>();


    public List<BigDecimal[]> getDoublesObject() {
        return doublesObject;
    }

    public int getIslandLength() {
        return islandLength;
    }

    public void setIslandLength(int islandLength) {
        dataBase.islandLength = islandLength;
    }

    public int getIslandWidth() {
        return islandWidth;
    }

    public void setIslandWidth(int islandWidth) {
        dataBase.islandWidth = islandWidth;
    }

    private int islandLength;
    private int islandWidth;

    public void islandDimensions() {
        this.islandLength = 3;
        this.islandWidth = 2;
    }

    public int[] whoToEatAndHowToEat(int operationNumber) {
        return switch (operationNumber) {
            case 1 -> new int[]{0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0};       //Волк
            case 2 -> new int[]{0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0};            //Удав
            case 3 -> new int[]{0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0};            //Лиса
            case 4 -> new int[]{0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0};      //Медведь
            case 5 -> new int[]{0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0};            //Орел
            case 6 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};              //Лошадь
            case 7 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};              //Олень
            case 8 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};              //Кролик
            case 9 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100};             //Мышь
            case 10 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};             //Коза
            case 11 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};             //Овца
            case 12 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100};           //Кабан
            case 13 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};             //Буйвол
            case 14 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100};            //Утка
            case 15 -> new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100};             //Гусеница
            default -> null;
        };
    }

    public BigDecimal[] animalParameters(int operationNumber) {
        return switch (operationNumber) {
            case 1 -> new BigDecimal[]{new BigDecimal("50"), new BigDecimal("30"), new BigDecimal("3"), new BigDecimal("8")};             //Волк
            case 2 -> new BigDecimal[]{new BigDecimal("15"), new BigDecimal("30"), new BigDecimal("1"), new BigDecimal("3")};             //Удав
            case 3 -> new BigDecimal[]{new BigDecimal("8"), new BigDecimal("30"), new BigDecimal("2"), new BigDecimal("2")};              //Лиса
            case 4 -> new BigDecimal[]{new BigDecimal("500"), new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("80")};            //Медведь
            case 5 -> new BigDecimal[]{new BigDecimal("6"), new BigDecimal("10"), new BigDecimal("3"), new BigDecimal("1")};              //Орел
            case 6 -> new BigDecimal[]{new BigDecimal("400"), new BigDecimal("20"), new BigDecimal("4"), new BigDecimal("60")};           //Лошадь
            case 7 -> new BigDecimal[]{new BigDecimal("300"), new BigDecimal("20"), new BigDecimal("4"), new BigDecimal("50")};           //Олень
            case 8 -> new BigDecimal[]{new BigDecimal("2"), new BigDecimal("150"), new BigDecimal("2"), new BigDecimal("0.45")};          //Кролик
            case 9 -> new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("500"), new BigDecimal("1"), new BigDecimal("0.01")};       //Мышь
            case 10 -> new BigDecimal[]{new BigDecimal("60"), new BigDecimal("140"), new BigDecimal("3"), new BigDecimal("10")};          //Коза
            case 11 -> new BigDecimal[]{new BigDecimal("70"), new BigDecimal("140"), new BigDecimal("3"), new BigDecimal("15")};          //Овца
            case 12 -> new BigDecimal[]{new BigDecimal("400"), new BigDecimal("50"), new BigDecimal("2"), new BigDecimal("50")};          //Кабан
            case 13 -> new BigDecimal[]{new BigDecimal("700"), new BigDecimal("10"), new BigDecimal("3"), new BigDecimal("100")};         //Буйвол
            case 14 -> new BigDecimal[]{new BigDecimal("1"), new BigDecimal("200"), new BigDecimal("4"), new BigDecimal("0.15")};         //Утка
            case 15 -> new BigDecimal[]{new BigDecimal("0.01"), new BigDecimal("1000"), new BigDecimal("0"), new BigDecimal("0")};        //Гусеница

            default -> null;
        };

    }

    public String giveMeAnIcon(int operationNumber) {
        return switch (operationNumber) {
            case 1 -> "\uD83D\uDC3A";             //Волк
            case 2 -> "\uD83D\uDC0D";             //Удав
            case 3 -> "\uD83E\uDD8A";              //Лиса
            case 4 -> "\uD83D\uDC3B";            //Медведь
            case 5 -> "\uD83E\uDD85";              //Орел
            case 6 -> "\uD83D\uDC0E";           //Лошадь
            case 7 -> "\uD83E\uDD8C";           //Олень
            case 8 -> "\uD83D\uDC07";          //Кролик
            case 9 -> "\uD83D\uDC01";       //Мышь
            case 10 -> "\uD83D\uDC10";          //Коза
            case 11 -> "\uD83D\uDC11";          //Овца
            case 12 -> "\uD83D\uDC17";          //Кабан
            case 13 -> "\uD83D\uDC03";         //Буйвол
            case 14 -> "\uD83E\uDD86";         //Утка
            case 15 -> "\uD83D\uDC1B";        //Гусеница

            default -> null;
        };
    }

    //animalName, icon, animalParameters[0], (int) animalParameters[3], whoToEatAndHowToEat, conditionX, conditionY
    public Animal allAnimal
    (String animalName, String icon, BigDecimal weight, int speedCell, BigDecimal maximumSatiety, int[] whoToEatAndHowToEat, int id) {
        return switch (animalName) {
            case "Wolf" ->
                    new Wolf(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);            //Волк
            case "Boa" ->
                    new Boa(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);             //Удав
            case "Fox" ->
                    new Fox(icon, weight, speedCell, maximumSatiety,  whoToEatAndHowToEat, id);             //Лиса
            case "Bear" ->
                    new Bear(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);         //Медведь
            case "Eagle" ->
                    new Eagle(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);           //Орел
            case "Horse" ->
                    new Horse(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);         //Лошадь
            case "Deep" ->
                    new Deer(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);           //Олень
            case "Rabbit" ->
                    new Rabbit(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);        //Кролик
            case "Mouse" ->
                    new Mouse(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);           //Мышь
            case "Goat" ->
                    new Goat(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);            //Коза
            case "Sheep" ->
                    new Sheep(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);           //Овца
            case "Boar" ->
                    new Boar(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);           //Кабан
            case "Buffalo" ->
                    new Buffalo(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);       //Буйвол
            case "Duck" ->
                    new Duck(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id);            //Утка
            case "Caterpillar" ->
                    new Caterpillar(icon, weight, speedCell, maximumSatiety, whoToEatAndHowToEat, id); //Гусеница
            default -> null;
        };
    }

    public Vegetation allVegetation() {
        BigDecimal weight = new BigDecimal("1.0");
        return new Grass(weight);            //Трава
    }

    public double[] vegetationParameters() {
        return new double[]{1, 200};
    }

    private DataBase() {
    }

    public static synchronized DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
            return dataBase;
        }
        return dataBase;
    }
}


