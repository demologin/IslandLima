package com.javarush.island.alimin.properties;

import com.javarush.island.alimin.entity.ecosystem.Types;

import java.util.HashMap;
import java.util.Map;

public class Properties {

    /*
    меняя константы FILL_PROBABILITY (вероятность заполнения ячейки при инициализации),
    BORN_PROBABILITY (вероятность рождения организма при инициализации), а также
    LOSE_WEIGHT_PERCENT (процент потери веса, в случае если животное не поело на данном такте),
    а также остальные, можно управлять симуляцией
     */

    private Properties() {
    }
    public static final int PERIOD = 1000;
    public static final int LOCATION_SIZE_ROW = 20;
    public static final int LOCATION_SIZE_COLUMN = 100;

    public static final int FILL_PROBABILITY = 80;
    public static final int BORN_PROBABILITY = 50;

    public static final double LIFE_THREATENING_WEIGHT_LOSS_INDEX = 2.0;

    public static final double LOSE_WEIGHT_PERCENT = 3;

    public static final Map<Types, OrganismParameters> organismProperties = new HashMap<>();

    static {
        organismProperties.put(Types.WOLF, new OrganismParameters(Types.WOLF.getName(), "\uD83D\uDC3A", 50, 30, 3, 8));
        organismProperties.put(Types.SNAKE,new OrganismParameters(Types.SNAKE.getName(), " \uD83D\uDC0D", 15,30,1,3));
        organismProperties.put(Types.FOX,new OrganismParameters(Types.FOX.getName(), " \uD83E\uDD8A", 8,30,2,2));
        organismProperties.put(Types.BEAR,new OrganismParameters(Types.BEAR.getName(), " \uD83D\uDC3B", 500,5,2,80));
        organismProperties.put(Types.EAGLE,new OrganismParameters(Types.EAGLE.getName(), " \uD83E\uDD85", 6,20,3,1));
        organismProperties.put(Types.HORSE,new OrganismParameters(Types.HORSE.getName(), " \uD83D\uDC0E", 400,20,4,60));
        organismProperties.put(Types.DEER,new OrganismParameters(Types.DEER.getName(), " \uD83E\uDD8C", 300,20,4,50));
        organismProperties.put(Types.RABBIT,new OrganismParameters(Types.RABBIT.getName(), " \uD83D\uDC07", 2,150,2,0.45));
        organismProperties.put(Types.MOUSE,new OrganismParameters(Types.MOUSE.getName(), " \uD83D\uDC01", 0.05,500,1,0.01));
        organismProperties.put(Types.GOAT,new OrganismParameters(Types.GOAT.getName(), " \uD83D\uDC10", 60,140,3,10));
        organismProperties.put(Types.BUFFALO,new OrganismParameters(Types.BUFFALO.getName(), " \uD83D\uDC03", 700,10,3,100));
        organismProperties.put(Types.SHEEP,new OrganismParameters(Types.SHEEP.getName()," \uD83D\uDC11", 70,140,3,15));
        organismProperties.put(Types.BOAR,new OrganismParameters(Types.BOAR.getName(), " \uD83D\uDC17", 400,50,2,50));
        organismProperties.put(Types.DUCK,new OrganismParameters(Types.DUCK.getName(), " \uD83E\uDD86", 1,200,4,0.15));
        organismProperties.put(Types.CATERPILLAR,new OrganismParameters(Types.CATERPILLAR.getName(), " \uD83D\uDC1B", 0.01,1000,0,0));
        organismProperties.put(Types.PLANT,new OrganismParameters(Types.PLANT.getName(), "\uD83C\uDF3F", 1,200,0,0));
    }

    public static final Map<Types, Map<Types, Integer>> eatProbability = Map.of(
            Types.WOLF, Map.of(
                    Types.HORSE, 10,
                    Types.DEER, 15,
                    Types.RABBIT, 60,
                    Types.MOUSE, 80,
                    Types.GOAT, 60,
                    Types.SHEEP, 70,
                    Types.BOAR, 15,
                    Types.BUFFALO, 10,
                    Types.DUCK, 40
                    ),
            Types.SNAKE, Map.of(
                    Types.RABBIT, 20,
                    Types.MOUSE, 40,
                    Types.DUCK, 10
            ),
            Types.FOX, Map.of(
                    Types.RABBIT, 70,
                    Types.MOUSE, 90,
                    Types.DUCK, 60,
                    Types.CATERPILLAR, 40
            ),
            Types.BEAR, Map.of(
                    Types.HORSE, 40,
                    Types.DEER, 80,
                    Types.RABBIT, 80,
                    Types.MOUSE, 90,
                    Types.GOAT, 70,
                    Types.SHEEP, 70,
                    Types.BOAR, 50,
                    Types.BUFFALO, 20,
                    Types.DUCK, 10
            ),
            Types.EAGLE, Map.of(
                    Types.RABBIT, 90,
                    Types.MOUSE, 90,
                    Types.DUCK, 80
            ),
            Types.MOUSE, Map.of(
                    Types.CATERPILLAR, 90
            ),
            Types.BOAR, Map.of(
                    Types.MOUSE, 50,
                    Types.CATERPILLAR, 90
            ),
            Types.DUCK, Map.of(
                    Types.CATERPILLAR, 90
            )
    );
}
