package com.javarush.island.uzienko.config;

import com.javarush.island.uzienko.entity.properties.GameProperties;
import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.exceptions.IslandException;
import com.javarush.island.uzienko.utils.YmlReader;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConfig {
    private static GameConfig instance;

    private static final String ENTITY_PACKAGE = "com.javarush.island.uzienko.entity.residents";
    private static final String RESIDENTS_YML = "uzienko-residents.yml";
    private static final String GAME_YML = "uzienko.yml";
    private GameProperties gameProperties;
    private final Map<String, Resident> context = new HashMap<>();

    private GameConfig() {
    }

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
            instance.contextInit();
            instance.gamePropertiesInit();
        }
        return instance;
    }

    public int getCols() {
        return gameProperties.getCols();
    }

    public int getRows() {
        return gameProperties.getRows();
    }

    public int getAnimalInCell() {
        return gameProperties.getAnimalInCell();
    }

    public int getMainLoopPeriod() {
        return gameProperties.getMainLoopPeriod();
    }

    public Map<String, Resident> getContext() {
        return context;
    }

    private void contextInit() {
        List<ResidentProperties> residentPropertiesList = YmlReader.getResidents(RESIDENTS_YML);
        for (ResidentProperties residentProperties : residentPropertiesList) {
            Resident newResident = newInstanceByName(residentProperties);
            context.put(residentProperties.getType(), newResident);
        }
    }

    private Resident newInstanceByName(ResidentProperties residentProperties) {
        try {
            Class<?> clazz = Class.forName(String.join(".",
                    ENTITY_PACKAGE,
                    residentProperties.getFamily(),
                    residentProperties.getType()));
            Constructor<?> constructor = clazz.getConstructor(ResidentProperties.class);
            return (Resident) constructor.newInstance(residentProperties);
        } catch (ReflectiveOperationException e) {
            throw new IslandException(e);
        }
    }

    private void gamePropertiesInit() {
        gameProperties = YmlReader.getGameProperties(GAME_YML);
    }
}
