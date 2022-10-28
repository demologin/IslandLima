package com.javarush.island.plugin.gameset;

import com.javarush.island.plugin.entity.Organism;

import java.util.HashMap;
import java.util.Map;

public class GamePreferences {
    private Map<Organism, Fields> preferences = new HashMap<>();
    private int[][] probabilities = new int[Organism.values().length][Organism.values().length];


    private static final GamePreferences instance = new GamePreferences();

    public static final int row = 30;
    public static final int col = 30;


    private GamePreferences(){
        initialize();
    }

    public void initialize(){
        //Заполняем настройки для кождого вида
        preferences.put( Organism.PLANT, new Fields("Растение", "*", 1, 1, 0, 20, 0, 0));
        preferences.put( Organism.BEAR, new Fields("Медведь", " \uD83D\uDC3B", 460, 2, 500, 5, 2,80));
        preferences.put( Organism.EAGLE, new Fields("Орел", " \uD83E\uDD85",5.5, 3, 6, 2, 3, 1));
        preferences.put( Organism.FOX, new Fields("Лиса", " \uD83E\uDD8A", 7, 2, 8, 3, 2, 2));
        preferences.put( Organism.SNAKE, new Fields("Змея", " \uD83D\uDC0D", 13.5, 1, 15, 3, 1, 3));
        preferences.put( Organism.WOLF, new Fields("Волк", " \uD83D\uDC3A", 46, 3, 50, 3, 3, 8));
        preferences.put( Organism.CATERPILLAR, new Fields("Гусеница", " \uD83D\uDC1B", 0.01, 1, 0.01, 15, 0, 0));
        preferences.put( Organism.COW, new Fields("Корова", " \uD83D\uDC10", 650, 3, 700, 3, 3, 100));
        preferences.put( Organism.DEER, new Fields("Олень", " \uD83E\uDD8C", 275, 4, 300, 3, 4, 50));
        preferences.put( Organism.DUCK, new Fields("Утка", " \uD83E\uDD86", 0.92, 4, 1, 6, 4, 0.15));
        preferences.put( Organism.MOUSE, new Fields("Хомяк", " \uD83D\uDC01", 1.75, 2, 2, 7, 2,0.45));
        preferences.put( Organism.HORSE, new Fields("Лошадь", " \uD83D\uDC0E", 370, 4, 400, 3, 4, 60));
        preferences.put( Organism.RABBIT, new Fields("Кенгуру", " \uD83D\uDC07", 275, 4, 300, 5, 4, 50));
        preferences.put( Organism.SHEEP, new Fields("Овца", " \uD83D\uDC11", 50, 3, 70, 14,3,20));

        //Заполняем настройки для вероятностей поедания
        probabilities[Organism.BEAR.ordinal()][Organism.SNAKE.ordinal()] = 80;
        probabilities[Organism.BEAR.ordinal()][Organism.COW.ordinal()] = 20;
        probabilities[Organism.BEAR.ordinal()][Organism.DEER.ordinal()] = 80;
        probabilities[Organism.BEAR.ordinal()][Organism.DUCK.ordinal()] = 10;
        probabilities[Organism.BEAR.ordinal()][Organism.MOUSE.ordinal()] = 90;
        probabilities[Organism.BEAR.ordinal()][Organism.HORSE.ordinal()] = 40;
        probabilities[Organism.BEAR.ordinal()][Organism.RABBIT.ordinal()] = 80;
        probabilities[Organism.BEAR.ordinal()][Organism.SHEEP.ordinal()] = 70;

        probabilities[Organism.EAGLE.ordinal()][Organism.FOX.ordinal()] = 10;
        probabilities[Organism.EAGLE.ordinal()][Organism.DUCK.ordinal()] = 80;
        probabilities[Organism.EAGLE.ordinal()][Organism.MOUSE.ordinal()] = 90;
        probabilities[Organism.EAGLE.ordinal()][Organism.RABBIT.ordinal()] = 90;

        probabilities[Organism.FOX.ordinal()][Organism.CATERPILLAR.ordinal()] = 40;
        probabilities[Organism.FOX.ordinal()][Organism.DUCK.ordinal()] = 60;
        probabilities[Organism.FOX.ordinal()][Organism.MOUSE.ordinal()] = 90;
        probabilities[Organism.FOX.ordinal()][Organism.RABBIT.ordinal()] = 70;

        probabilities[Organism.SNAKE.ordinal()][Organism.FOX.ordinal()] = 15;
        probabilities[Organism.SNAKE.ordinal()][Organism.DUCK.ordinal()] = 10;
        probabilities[Organism.SNAKE.ordinal()][Organism.MOUSE.ordinal()] = 40;
        probabilities[Organism.SNAKE.ordinal()][Organism.RABBIT.ordinal()] = 20;

        probabilities[Organism.WOLF.ordinal()][Organism.COW.ordinal()] = 10;
        probabilities[Organism.WOLF.ordinal()][Organism.DEER.ordinal()] = 15;
        probabilities[Organism.WOLF.ordinal()][Organism.DUCK.ordinal()] = 40;
        probabilities[Organism.WOLF.ordinal()][Organism.MOUSE.ordinal()] = 80;
        probabilities[Organism.WOLF.ordinal()][Organism.HORSE.ordinal()] = 10;
        probabilities[Organism.WOLF.ordinal()][Organism.RABBIT.ordinal()] = 15;
        probabilities[Organism.WOLF.ordinal()][Organism.SHEEP.ordinal()] = 70;

        probabilities[Organism.CATERPILLAR.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.COW.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.DEER.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.DUCK.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.MOUSE.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.HORSE.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.RABBIT.ordinal()][Organism.PLANT.ordinal()] = 100;
        probabilities[Organism.SHEEP.ordinal()][Organism.PLANT.ordinal()] = 100;

    }

    public static GamePreferences getInstance() {
        return instance;
    }

    public Map<Organism, Fields> getMap(){
        return instance.preferences;
    }

    public int[][] getProbabilities() {
        return probabilities;
    }
}
