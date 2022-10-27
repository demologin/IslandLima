package com.javarush.island.osypenko.constants;

import com.javarush.island.osypenko.pref.EntitiesType;
import com.javarush.island.osypenko.pref.ObjectPrefs;

import java.util.HashMap;
import java.util.Map;

public class CharacteristicsEntities {
    private static final Map<EntitiesType, ObjectPrefs> characteristicsEntities = new HashMap<>();
    private static final int[][] howLikelyTheAnimalIsToEatTheFood = new int[EntitiesType.values().length][EntitiesType.values().length];

    static  {
        characteristicsEntities.put(EntitiesType.WOLF, new ObjectPrefs("Волк", "\uD83D\uDC3A", 50, 30, 3, 8, 25));
        characteristicsEntities.put(EntitiesType.SNAKE, new ObjectPrefs("Удав", "\uD83D\uDC0D", 15, 30, 1, 3, 7.5));
        characteristicsEntities.put(EntitiesType.FOX, new ObjectPrefs("Лиса", "\uD83E\uDD8A", 8, 30, 2, 2, 4));
        characteristicsEntities.put(EntitiesType.BEAR, new ObjectPrefs("Медведь", "\uD83D\uDC3B", 500, 5, 2, 80, 250));
        characteristicsEntities.put(EntitiesType.EAGLE, new ObjectPrefs("Орел", "\uD83E\uDD85", 6, 20, 3, 1, 3));
        characteristicsEntities.put(EntitiesType.HORSE, new ObjectPrefs("Лошадь", "\uD83D\uDC0E", 400, 20, 4, 60, 200));
        characteristicsEntities.put(EntitiesType.DEER, new ObjectPrefs("Олень", "\uD83E\uDD8C", 300, 20, 4, 50, 150));
        characteristicsEntities.put(EntitiesType.RABBIT, new ObjectPrefs("Кролик", "\uD83D\uDC07", 2, 150, 2, 0.45, 1));
        characteristicsEntities.put(EntitiesType.MOUSE, new ObjectPrefs("Мышь", "\uD83D\uDC01", 0.05, 500, 1, 0.01, 0.02));
        characteristicsEntities.put(EntitiesType.GOAT, new ObjectPrefs("Коза", "\uD83D\uDC10", 60, 140, 3, 10, 30));
        characteristicsEntities.put(EntitiesType.SHEEP, new ObjectPrefs("Овца", "\uD83D\uDC11", 70, 140, 3, 15, 35));
        characteristicsEntities.put(EntitiesType.BOAR, new ObjectPrefs("Кабан", "\uD83D\uDC17", 400, 50,2,50, 200));
        characteristicsEntities.put(EntitiesType.BUFFALO, new ObjectPrefs("Буйвол", "\uD83D\uDC03", 700, 10, 3, 100, 350));
        characteristicsEntities.put(EntitiesType.DUCK, new ObjectPrefs("Утка", "\uD83E\uDD86", 1, 200, 4, 0.15, 0.5));
        characteristicsEntities.put(EntitiesType.CATERPILLAR, new ObjectPrefs("Гусеница", "\uD83D\uDC1B", 0.01, 1000, 0,0, 0.005));
        characteristicsEntities.put(EntitiesType.GRASS, new ObjectPrefs("Трава", "\uD83C\uDF3F", 1, 200, 0, 0, 0));

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.HORSE.ordinal()] = 10;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.DEER.ordinal()] = 15;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.RABBIT.ordinal()] = 60;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.MOUSE.ordinal()] = 70;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.GOAT.ordinal()] = 60;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.SHEEP.ordinal()] = 70;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.BOAR.ordinal()] = 15;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.BUFFALO.ordinal()] = 10;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.WOLF.ordinal()][EntitiesType.DUCK.ordinal()] = 40;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.SNAKE.ordinal()][EntitiesType.FOX.ordinal()] = 15;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.SNAKE.ordinal()][EntitiesType.RABBIT.ordinal()] = 20;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.SNAKE.ordinal()][EntitiesType.MOUSE.ordinal()] = 30;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.SNAKE.ordinal()][EntitiesType.DUCK.ordinal()] = 10;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.FOX.ordinal()][EntitiesType.RABBIT.ordinal()] = 70;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.FOX.ordinal()][EntitiesType.MOUSE.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.FOX.ordinal()][EntitiesType.DUCK.ordinal()] = 60;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.FOX.ordinal()][EntitiesType.CATERPILLAR.ordinal()] = 40;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.SNAKE.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.HORSE.ordinal()] = 40;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.DEER.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.RABBIT.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.MOUSE.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.GOAT.ordinal()] = 70;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.SHEEP.ordinal()] = 70;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.BOAR.ordinal()] = 50;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.BUFFALO.ordinal()] = 20;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BEAR.ordinal()][EntitiesType.DUCK.ordinal()] = 10;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.EAGLE.ordinal()][EntitiesType.FOX.ordinal()] = 10;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.EAGLE.ordinal()][EntitiesType.RABBIT.ordinal()] = 90;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.EAGLE.ordinal()][EntitiesType.MOUSE.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.EAGLE.ordinal()][EntitiesType.DUCK.ordinal()] = 80;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.HORSE.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.DEER.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.RABBIT.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.MOUSE.ordinal()][EntitiesType.CATERPILLAR.ordinal()] = 90;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.MOUSE.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.GOAT.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.SHEEP.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BOAR.ordinal()][EntitiesType.MOUSE.ordinal()] = 40;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BOAR.ordinal()][EntitiesType.CATERPILLAR.ordinal()] = 90;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BOAR.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.BUFFALO.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.DUCK.ordinal()][EntitiesType.CATERPILLAR.ordinal()] = 80;
        howLikelyTheAnimalIsToEatTheFood[EntitiesType.DUCK.ordinal()][EntitiesType.GRASS.ordinal()] = 70;

        howLikelyTheAnimalIsToEatTheFood[EntitiesType.CATERPILLAR.ordinal()][EntitiesType.GRASS.ordinal()] = 70;
    }

    public static EntitiesType[] getWolfEat() {
        return new EntitiesType[]{EntitiesType.HORSE, EntitiesType.DEER, EntitiesType.RABBIT, EntitiesType.MOUSE, EntitiesType.GOAT, EntitiesType.SHEEP, EntitiesType.BOAR, EntitiesType.BUFFALO, EntitiesType.DUCK};
    }

    public static EntitiesType[] getSnakeEat() {
        return new EntitiesType[]{EntitiesType.FOX, EntitiesType.RABBIT, EntitiesType.MOUSE, EntitiesType.DUCK};
    }

    public static EntitiesType[] getFoxEat() {
        return new EntitiesType[]{EntitiesType.RABBIT, EntitiesType.MOUSE, EntitiesType.DUCK, EntitiesType.CATERPILLAR};
    }

    public static EntitiesType[] getBearEat() {
        return new EntitiesType[]{EntitiesType.SNAKE, EntitiesType.HORSE, EntitiesType.DEER, EntitiesType.RABBIT, EntitiesType.MOUSE, EntitiesType.GOAT, EntitiesType.SHEEP, EntitiesType.BOAR, EntitiesType.BUFFALO, EntitiesType.DUCK};
    }

    public static EntitiesType[] getEagleEat() {
        return new EntitiesType[]{EntitiesType.FOX, EntitiesType.RABBIT, EntitiesType.MOUSE, EntitiesType.DUCK};
    }

    public static EntitiesType[] getHorseEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getDeerEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getRabbitEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getMouseEat() {
        return new EntitiesType[]{EntitiesType.CATERPILLAR, EntitiesType.GRASS};
    }

    public static EntitiesType[] getGoatEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getSheepEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getBoarEat() {
        return new EntitiesType[]{EntitiesType.MOUSE, EntitiesType.CATERPILLAR, EntitiesType.GRASS};
    }

    public static EntitiesType[] getBuffaloEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static EntitiesType[] getDuckEat() {
        return new EntitiesType[]{EntitiesType.CATERPILLAR, EntitiesType.GRASS};
    }

    public static EntitiesType[] getCaterpillarEat() {
        return new EntitiesType[]{EntitiesType.GRASS};
    }

    public static Map<EntitiesType, ObjectPrefs> getCharacteristicsEntities() {
        return characteristicsEntities;
    }

    public static int[][] getHowLikelyTheAnimalIsToEatTheFood() {
        return howLikelyTheAnimalIsToEatTheFood;
    }
}
