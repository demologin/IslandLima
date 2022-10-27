package com.javarush.island.kolesnikova.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;

import static com.javarush.island.kolesnikova.constants.PropertiesUnit.UnitsName.*;


@AllArgsConstructor
@Getter
public class PropertiesUnit {


    private final String icon;
    private final String name;
    private final Double weight;
    private final int speed;
    private final int maxUnitsInCell;
    private final Double kilogramOfFood;

    public enum UnitsName {
                WOLF, SNAKE, FOX, BEAR, EAGLE, HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, HOG, BUFFALO, DUCK, CATERPILLAR, HERB

    }

    public static EnumSet<UnitsName> allUnits() {
        return EnumSet.allOf(UnitsName.class);
    }

    private static final Map<UnitsName, PropertiesUnit> unitsPropertiesMap = Map.ofEntries(
            Map.entry(WOLF, new PropertiesUnit("🐺", "Волк", 50.0, 3, 30, 8.0)),
            Map.entry(SNAKE, new PropertiesUnit("🐍", "Змея", 15.0, 1, 30, 3.0)),
            Map.entry(FOX, new PropertiesUnit("🦊", "Лиса", 8.0, 2, 30, 2.0)),
            Map.entry(BEAR, new PropertiesUnit("🐻", "Мишка", 500.0, 2, 5, 80.0)),
            Map.entry(EAGLE, new PropertiesUnit("🦅", "Орел", 6.0, 3, 20, 1.0)),
            Map.entry(HORSE, new PropertiesUnit("🎠", "Лошадь", 400.0, 4, 20, 60.0)),
            Map.entry(DEER, new PropertiesUnit("🦌", "Олень", 300.0, 4, 20, 50.0)),
            Map.entry(RABBIT, new PropertiesUnit("🐇", "Заяц", 2., 2, 150, 0.45)),
            Map.entry(MOUSE, new PropertiesUnit("🐁", "Мышка", 0.05, 1, 500, 0.01)),
            Map.entry(GOAT, new PropertiesUnit("🐐", "Козлик", 60.0, 3, 140, 10.0)),
            Map.entry(SHEEP, new PropertiesUnit("🐑", "Овечка", 70.0, 3, 140, 15.0)),
            Map.entry(HOG, new PropertiesUnit("🐏", "Кабан", 400.0, 2, 50, 50.0)),
            Map.entry(BUFFALO, new PropertiesUnit("🐃", "Буйвол", 700.0, 3, 10, 100.0)),
            Map.entry(DUCK, new PropertiesUnit("🦆", "Утка", 1.0, 4, 200, 0.15)),
            Map.entry(CATERPILLAR, new PropertiesUnit("🐛", "Гусеница", 0.01, 3, 1000, 8.0)),
            Map.entry(HERB, new PropertiesUnit("🌿", "Трава", 1., 0, 200, 0.)));


    public static PropertiesUnit unitsProperties(UnitsName unitsName) {
        return unitsPropertiesMap.get(unitsName);
    }


    private static final int[][] chanceToHaveDinner = new int[UnitsName.values().length][UnitsName.values().length];


    public static int[][] chanceToHaveDinner() {

        //WOLF
        chanceToHaveDinner[WOLF.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][HORSE.ordinal()] = 15;
        chanceToHaveDinner[WOLF.ordinal()][DEER.ordinal()] = 15;
        chanceToHaveDinner[WOLF.ordinal()][RABBIT.ordinal()] = 60;
        chanceToHaveDinner[WOLF.ordinal()][MOUSE.ordinal()] = 80;
        chanceToHaveDinner[WOLF.ordinal()][GOAT.ordinal()] = 60;
        chanceToHaveDinner[WOLF.ordinal()][SHEEP.ordinal()] = 70;
        chanceToHaveDinner[WOLF.ordinal()][HOG.ordinal()] = 15;
        chanceToHaveDinner[WOLF.ordinal()][BUFFALO.ordinal()] = 10;
        chanceToHaveDinner[WOLF.ordinal()][DUCK.ordinal()] = 40;
        chanceToHaveDinner[WOLF.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[WOLF.ordinal()][HERB.ordinal()] = 0;


        //SNAKE
        chanceToHaveDinner[SNAKE.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][FOX.ordinal()] = 15;
        chanceToHaveDinner[SNAKE.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][RABBIT.ordinal()] = 20;
        chanceToHaveDinner[SNAKE.ordinal()][MOUSE.ordinal()] = 40;
        chanceToHaveDinner[SNAKE.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][DUCK.ordinal()] = 10;
        chanceToHaveDinner[SNAKE.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[SNAKE.ordinal()][HERB.ordinal()] = 0;

        //FOX
        chanceToHaveDinner[FOX.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][RABBIT.ordinal()] = 70;
        chanceToHaveDinner[FOX.ordinal()][MOUSE.ordinal()] = 90;
        chanceToHaveDinner[FOX.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[FOX.ordinal()][DUCK.ordinal()] = 60;
        chanceToHaveDinner[FOX.ordinal()][CATERPILLAR.ordinal()] = 40;
        chanceToHaveDinner[FOX.ordinal()][HERB.ordinal()] = 0;

        //BEAR
        chanceToHaveDinner[BEAR.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[BEAR.ordinal()][SNAKE.ordinal()] = 80;
        chanceToHaveDinner[BEAR.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[BEAR.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[BEAR.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[BEAR.ordinal()][HORSE.ordinal()] = 40;
        chanceToHaveDinner[BEAR.ordinal()][DEER.ordinal()] = 80;
        chanceToHaveDinner[BEAR.ordinal()][RABBIT.ordinal()] = 80;
        chanceToHaveDinner[BEAR.ordinal()][MOUSE.ordinal()] = 90;
        chanceToHaveDinner[BEAR.ordinal()][GOAT.ordinal()] = 70;
        chanceToHaveDinner[BEAR.ordinal()][SHEEP.ordinal()] = 70;
        chanceToHaveDinner[BEAR.ordinal()][HOG.ordinal()] = 50;
        chanceToHaveDinner[BEAR.ordinal()][BUFFALO.ordinal()] = 20;
        chanceToHaveDinner[BEAR.ordinal()][DUCK.ordinal()] = 10;
        chanceToHaveDinner[BEAR.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[BEAR.ordinal()][HERB.ordinal()] = 0;

        //EAGLE
        chanceToHaveDinner[EAGLE.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][FOX.ordinal()] = 10;
        chanceToHaveDinner[EAGLE.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][RABBIT.ordinal()] = 90;
        chanceToHaveDinner[EAGLE.ordinal()][MOUSE.ordinal()] = 90;
        chanceToHaveDinner[EAGLE.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][DUCK.ordinal()] = 80;
        chanceToHaveDinner[EAGLE.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[EAGLE.ordinal()][HERB.ordinal()] = 0;

        //HORSE
        chanceToHaveDinner[HORSE.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[HORSE.ordinal()][HERB.ordinal()] = 100;


        //DEER
        chanceToHaveDinner[DEER.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[DEER.ordinal()][HERB.ordinal()] = 100;

        //RABBIT
        chanceToHaveDinner[RABBIT.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[RABBIT.ordinal()][HERB.ordinal()]= 100;


        //MOUSE
        chanceToHaveDinner[MOUSE.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[MOUSE.ordinal()][CATERPILLAR.ordinal()] = 90;
        chanceToHaveDinner[MOUSE.ordinal()][HERB.ordinal()] = 100;

        //GOAT
        chanceToHaveDinner[GOAT.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[GOAT.ordinal()][HERB.ordinal()] = 100;

        //SHEEP
        chanceToHaveDinner[SHEEP.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[SHEEP.ordinal()][HERB.ordinal()] = 100;

        //HOG
        chanceToHaveDinner[HOG.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][MOUSE.ordinal()] = 50;
        chanceToHaveDinner[HOG.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[HOG.ordinal()][CATERPILLAR.ordinal()] = 90;
        chanceToHaveDinner[HOG.ordinal()][HERB.ordinal()] =100;

        //BUFFALO
        chanceToHaveDinner[BUFFALO.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][MOUSE.ordinal()] = 50;
        chanceToHaveDinner[BUFFALO.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[BUFFALO.ordinal()][HERB.ordinal()] = 100;

        //DUCK
        chanceToHaveDinner[DUCK.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[DUCK.ordinal()][CATERPILLAR.ordinal()] = 90;
        chanceToHaveDinner[DUCK.ordinal()][HERB.ordinal()] = 100;

        //CATERPILLAR
        chanceToHaveDinner[CATERPILLAR.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[CATERPILLAR.ordinal()][HERB.ordinal()] = 0;

        //HERB
        chanceToHaveDinner[HERB.ordinal()][WOLF.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][SNAKE.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][FOX.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][BEAR.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][EAGLE.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][HORSE.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][DEER.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][RABBIT.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][MOUSE.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][GOAT.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][SHEEP.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][HOG.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][BUFFALO.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][DUCK.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][CATERPILLAR.ordinal()] = 0;
        chanceToHaveDinner[HERB.ordinal()][HERB.ordinal()] = 0;


        return chanceToHaveDinner;
    }
}



