package com.javarush.island.ivanilov.entities;

import com.javarush.island.ivanilov.game.Settings;
import com.javarush.island.ivanilov.utils.Dice;

public enum Terrain {
    BASIC_TERRAIN,
    RIVER,
    MOUNTAINS;

    public static Terrain generateTerritory() {
        double dice = Dice.random(0, 1.0);
        if (dice <= Settings.settings.BASIC_TERRAIN_CHANCE) return BASIC_TERRAIN;
        if (dice <= Settings.settings.MOUNTAINS_CHANCE) return RIVER;
        return MOUNTAINS;
    }
}
