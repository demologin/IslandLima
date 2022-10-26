package com.javarush.island.sternard.settings;

import com.google.gson.Gson;
import com.javarush.island.sternard.actions.Eat;
import com.javarush.island.sternard.actions.Move;
import com.javarush.island.sternard.actions.Relax;
import com.javarush.island.sternard.actions.Reproduce;
import com.javarush.island.sternard.annotation.Check;
import com.javarush.island.sternard.enumeration.ConsoleColors;
import com.javarush.island.sternard.exception.HandlerExceptions;
import com.javarush.island.sternard.organisms.*;
import com.javarush.island.sternard.organisms.parents.Organism;
import com.javarush.island.sternard.result.Result;
import com.javarush.island.sternard.result.ResultCode;
import com.javarush.island.sternard.utils.CheckInputData;
import com.javarush.island.sternard.utils.GameLogger;
import com.javarush.island.sternard.utils.PathFinder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.javarush.island.sternard.constant.lang.English.*;

@SuppressWarnings({"UnusedDeclaration"})
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {

    static {
        //This line can be removed if LOG4J2.Properties will move in to resources
        System.setProperty("log4j2.configurationFile", "src/main/resources/sternard/log4j2.properties");
    }

    private static final String GAME_SETTINGS_JSON = "src/main/resources/sternard/GameSettings.json";
    @Check(message = INVALID_FILE_PATH)
    public String pathToOrganismsProperty;

    @Check(message = NOT_NULL_AND_NOT_EMPTY)
    private String log4j2_properties;

    @Check(message = NOT_NULL_AND_NOT_EMPTY)
    private String textColorStatisticValue;

    @Check(message = NOT_NULL_AND_NOT_EMPTY)
    private String statisticTextColorDay;

    @Check(message = NOT_NULL_AND_NOT_EMPTY)
    private String textColorStatisticKey;

    @Check(minValue = 1, maxValue = 25, message = VALUE_MUST_BE + "1-25.")
    private int statisticColumns;

    @Check(minValue = 1, maxValue = 200, message = VALUE_MUST_BE + "1-200.")
    private int widthMap;

    @Check(minValue = 1, maxValue = 200, message = VALUE_MUST_BE + "1-200.")
    private int heightMap;

    @Check(minValue = 1, maxValue = 50, message = VALUE_MUST_BE + "1-50.")
    private int maxAnimalCountInCell;

    @Check(message = VALUE_MUST_BE + "0-100.")
    private int minEnergyToDie;

    @Check(message = VALUE_MUST_BE + "0-100.")
    private double reduceEnergyPercent;

    @Check(message = VALUE_MUST_BE + "0-100.")
    private double increaseEnergyPercent;

    @Check(minValue = 1, maxValue = 100000, message = VALUE_MUST_BE + "1-100000.")
    private int plantGrowPeriod;

    @Check(minValue = 1, message = VALUE_MUST_BE + "1-100.")
    private int energyForNewAnimals;

    @Check(minValue = 1, maxValue = 100000, message = VALUE_MUST_BE + "1-100000.")
    private int showStatisticsPeriod;

    @Check(minValue = 1, maxValue = 100000, message = VALUE_MUST_BE + "1-100000.")
    private int cellServicePeriod;

    @Check(minValue = 1, maxValue = 10000, message = VALUE_MUST_BE + "1-10000.")
    private int maxNumberOfDays;

    private boolean exceptionShowStackTrace;

    @Check(message = FIELD_IS_EMPTY)
    private Map<String, Integer> actions;

    @Check(message = FIELD_IS_EMPTY)
    private String[] directionsToMove;

    @Check(message = FIELD_IS_EMPTY)
    private Map<String, String[]> organismType;

    private static final Map<String, Class<?>> classesActions = new HashMap<>() {{
        put("Eat", Eat.class);
        put("Move", Move.class);
        put("Relax", Relax.class);
        put("Reproduce", Reproduce.class);
    }};

    private static final Map<String, Class<? extends Organism>> classesOrganisms = new HashMap<>() {{
        put("Bear", Bear.class);
        put("Boar", Boar.class);
        put("Buffalo", Buffalo.class);
        put("Caterpillar", Caterpillar.class);
        put("Chestnut", Chestnut.class);
        put("Deer", Deer.class);
        put("Duck", Duck.class);
        put("Eagle", Eagle.class);
        put("Fox", Fox.class);
        put("Goat", Goat.class);
        put("Horse", Horse.class);
        put("Lion", Lion.class);
        put("Mouse", Mouse.class);
        put("Mushroom", Mushroom.class);
        put("Palm", Palm.class);
        put("Panther", Panther.class);
        put("Rabbit", Rabbit.class);
        put("Sheep", Sheep.class);
        put("Snake", Snake.class);
        put("Spike", Spike.class);
        put("Sprig", Sprig.class);
        put("Tree", Tree.class);
        put("Wolf", Wolf.class);
    }};

    public static Settings get() {
        String pathToSettingsFile = PathFinder.convertPathForAllOS(GAME_SETTINGS_JSON);
        try (FileReader fileReader = new FileReader(pathToSettingsFile)) {
            Settings settingsFromJSON = new Gson().fromJson(fileReader, Settings.class);
            Result validateSettingsResultCode = CheckInputData.check(settingsFromJSON);
            if (validateSettingsResultCode.getResultCode() == ResultCode.OK)
                return settingsFromJSON;
            else {
                String errorMessage = validateSettingsResultCode.getMessage();
                GameLogger.getLog().error(errorMessage);
                throw new HandlerExceptions(errorMessage);
            }
        } catch (IOException e) {
            GameLogger.getLog().error(e.getMessage(), e);
            throw new HandlerExceptions(FILE_ERROR + pathToSettingsFile, e.getStackTrace());
        }
    }

    public String getPathToOrganismsProperty() {
        return PathFinder.convertPathForAllOS(pathToOrganismsProperty);
    }

    private String getColor(String color) {
        try {
            return ConsoleColors.valueOf(color.toUpperCase()).getAnsiColor();
        } catch (IllegalArgumentException e) {
            GameLogger.getLog().warn(e.getMessage());
            throw new HandlerExceptions(NO_SUCH_COLOR_IN_CONSOLE_COLORS);
        }
    }

    public String getStatisticTextColorDay() {
        return getColor(statisticTextColorDay);
    }

    public String getTextColorStatisticKey() {
        return getColor(textColorStatisticKey);
    }

    public String getTextColorStatisticValue() {
        return getColor(textColorStatisticValue);
    }

    public static Map<String, Class<?>> getClassesActions() {
        return classesActions;
    }

    public static Map<String, Class<? extends Organism>> getClassesOrganisms() {
        return classesOrganisms;
    }
}
