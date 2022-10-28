package com.javarush.island.shubchynskyi.settings;

import static com.javarush.island.shubchynskyi.settings.EntitySettings.*;

public class GameSettings {
    private GameSettings() {
    }

    public static final int GAME_FIELD_WIDTH = 100;
    public static final int GAME_FIELD_HEIGHT = 20;

    public static final int UPDATE_PERIOD = 1000;
    public static final int MAX_TICKS = 150;

    public static final int CHEAT_LEVEL = 5; // Decreases the number of objects

    public static final int ANIMAL_PERCENT_SATIETY_TO_SPAWN = 65;
    public static final int ANIMAL_PERCENT_SPAWN_CHANCE = 5;
    public static final int ANIMAL_PERCENT_WEIGHT_LOSS = 10;
    public static final int ANIMAL_BABY_MAX_COUNT = 3;

    public static final int PLANT_PERCENT_SPAWN_CHANCE = 50;
    public static final int PLANT_GROW_AND_SPREADING_CHANCE = 20;

    public static final String[] GAME_STOP_CONDITIONS =
            {grassAvatar, treeAvatar, mouseAvatar, caterpillarAvatar};

}
