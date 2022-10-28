package com.javarush.island.ivanilov.game;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings {
    public static Settings settings = null;

    public final int ROWS;
    public final int COLUMNS;
    public final int ITERATION_SPEED_IN_MILLIS;
    public final double MIN_CHANCE_OF_SPAWN;
    public final int MAX_PLANT_MASS_IN_CELL;
    public final double PLANT_GROWTH_TEMPO;
    public final double BASIC_TERRAIN_CHANCE;
    public final double MOUNTAINS_CHANCE;
    public final double MIN_CHANCE_OF_NEW_POPULATION;
    public final int CARRION_DIVIDER;
    public final double CARRION_DECREASE_AT_THE_END_OF_ITERATION_MULTIPLIER;

    public Settings() {
        ROWS = 100;
        COLUMNS = 20;
        ITERATION_SPEED_IN_MILLIS = 500;
        MIN_CHANCE_OF_SPAWN = 0.25;
        MAX_PLANT_MASS_IN_CELL = 75000;
        PLANT_GROWTH_TEMPO = 1.03;
        BASIC_TERRAIN_CHANCE = 0.8;
        MOUNTAINS_CHANCE = 0.95;
        MIN_CHANCE_OF_NEW_POPULATION = 0.95;
        CARRION_DIVIDER = 5;
        CARRION_DECREASE_AT_THE_END_OF_ITERATION_MULTIPLIER = 3.0;
        settings = this;
    }

    public void parseSettings() {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path gameSettings = Path.of("GameSettings");

        if (!Files.isRegularFile(gameSettings))
            return;

        try {
            settings = mapper.readValue(gameSettings.toFile(), Settings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
