package com.javarush.island.maximov.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.maximov.exception.GlobalSettingsException;
import com.javarush.island.maximov.utils.ColorCode;
import com.javarush.island.maximov.utils.LineCode;
import com.javarush.island.maximov.world.World;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GlobalSettings {
    public static final String NAME = "name";
    public static final String LEGEND = "legend";
    public static final String SYMBOL = "symbol";
    public static final String MAX_WEIGHT = "maxWeight";
    public static final String WEIGHT_LOSS = "weightLoss";
    public static final String MAX_IN_TITLE = "maxInTitle";
    public static final String SPEED = "speed";
    public static final String WEIGHT_FOR_FULL = "weightForFull";
    public static final String MODE_OF_NUTRITION = "modeOfNutrition";
    public static final String SHOW_ON_MAP = "showOnMap";
    public static final String EAT_PROBABILITY_FAMILY_ID_2 = "EatProbability_FamilyId_2";
    public static final String EAT_PROBABILITY_FAMILY_ID_3 = "EatProbability_FamilyId_3";
    public static final String EAT_PROBABILITY_FAMILY_ID_6 = "EatProbability_FamilyId_6";
    public static final String EAT_PROBABILITY_FAMILY_ID_7 = "EatProbability_FamilyId_7";
    public static final String EAT_PROBABILITY_FAMILY_ID_8 = "EatProbability_FamilyId_8";
    public static final String EAT_PROBABILITY_FAMILY_ID_9 = "EatProbability_FamilyId_9";
    public static final String EAT_PROBABILITY_FAMILY_ID_10 = "EatProbability_FamilyId_10";
    public static final String EAT_PROBABILITY_FAMILY_ID_11 = "EatProbability_FamilyId_11";
    public static final String EAT_PROBABILITY_FAMILY_ID_12 = "EatProbability_FamilyId_12";
    public static final String EAT_PROBABILITY_FAMILY_ID_13 = "EatProbability_FamilyId_13";
    public static final String EAT_PROBABILITY_FAMILY_ID_14 = "EatProbability_FamilyId_14";
    public static final String EAT_PROBABILITY_FAMILY_ID_15 = "EatProbability_FamilyId_15";
    public static final String EAT_PROBABILITY_FAMILY_ID_16 = "EatProbability_FamilyId_16";
    public static final String CARNIVORES = "CARNIVORES";
    public static final String HERBIVORES = "HERBIVORES";
    public static final String OMNIVOROUS = "OMNIVOROUS";
    public static final String TRUE = "true";
    private int mapSizeWidth;
    private int mapSizeHeight;
    private final Map<Integer, HashMap<String, String>> settings = new HashMap<>();
    private final Map<Integer, String> map = new TreeMap<>();
    private final EnumMap<MapLegend, ColorCode> mapColor = new EnumMap<>(MapLegend.class);
    private final EnumMap<MapLegend, String> mapLegend = new EnumMap<>(MapLegend.class);
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final EnumMap<MapLegend, String> mapPicture = new EnumMap<>(MapLegend.class);
    private final EnumMap<LineCode, String> statisticLine = new EnumMap<>(LineCode.class);
    private final Map<String, MapLegend> mapLegendRevers = new HashMap<>();
    private ColorCode mapColorBackGround;
    private int statisticSizeOfNames;
    private int statisticSizeFrameAfterNames;
    private String statisticNameSymbol;
    private ColorCode statisticNameColorTurn;
    private ColorCode statisticNameColorLine;
    private ColorCode statisticNameColorPredator;
    private ColorCode statisticNameColorHerbivore;
    private ColorCode statisticNameColorOmnivorous;
    private ColorCode statisticNameColorUnknown;
    private ColorCode statisticNumbersColor;
    private ColorCode statisticNumbersPositiveColor;
    private ColorCode statisticNumbersNegativeColor;
    private int reproductionChance;

    public void defaultSettingFile(String fileName) throws IOException {
        createDefaultSettingFile();
        saveDefaultSettingFile(fileName);
    }

    private void saveDefaultSettingFile(String fileName) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        mapper.writeValue(new File(fileName), this);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private void createDefaultSettingFile() {
        HashMap<String, String> creature;

        // Global settings
        mapSizeWidth = 100;
        mapSizeHeight = 20;
        reproductionChance = 30;

        mapLegend.put(MapLegend.DEFAULT, "@");
        mapLegend.put(MapLegend.MOUNTAIN, "@");
        mapLegend.put(MapLegend.RIVER, "~");
        mapLegend.put(MapLegend.FIELD, ".");

        mapColorBackGround = ColorCode.BACK_WHITE;
        mapColor.put(MapLegend.DEFAULT, ColorCode.FONT_GRAY);
        mapColor.put(MapLegend.MOUNTAIN, ColorCode.FONT_GRAY);
        mapColor.put(MapLegend.RIVER, ColorCode.FONT_BLUE);
        mapColor.put(MapLegend.FIELD, ColorCode.FONT_GREEN);

        mapPicture.put(MapLegend.DEFAULT, "\u26F0");
        mapPicture.put(MapLegend.MOUNTAIN, "\u26F0");
        mapPicture.put(MapLegend.RIVER, "\uD83C\uDF0A");
        mapPicture.put(MapLegend.FIELD, "\uD83C\uDF43");

        statisticLine.put(LineCode.LINE_UP_RIGHT, "\u255A");
        statisticLine.put(LineCode.LINE_UP_LEFT, "\u255D");
        statisticLine.put(LineCode.LINE_DOWN_RIGHT, "\u2554");
        statisticLine.put(LineCode.LINE_DOWN_LEFT, "\u2557");
        statisticLine.put(LineCode.LINE_HORIZONTAL, "\u2550");
        statisticLine.put(LineCode.LINE_VERTICAL, "\u2551");
        statisticLine.put(LineCode.LINE_VERTICAL_LEFT, "\u2563");
        statisticLine.put(LineCode.LINE_VERTICAL_RIGHT, "\u2560");

        statisticNameColorLine = ColorCode.FONT_DARK_BROWN;
        statisticNameColorPredator = ColorCode.FONT_DARK_RED;
        statisticNameColorHerbivore = ColorCode.FONT_DARK_GREEN;
        statisticNameColorOmnivorous = ColorCode.FONT_VINOUS;
        statisticNameColorUnknown = ColorCode.FONT_GRAY;
        statisticNameColorTurn = ColorCode.FONT_DARK_BLUE;

        statisticNumbersColor = ColorCode.FONT_AMBER;
        statisticNumbersPositiveColor = ColorCode.FONT_DARK_GREEN;
        statisticNumbersNegativeColor = ColorCode.FONT_DARK_RED;

        statisticSizeOfNames = 12;
        statisticSizeFrameAfterNames = 20;
        statisticNameSymbol = "*";

        map.put(1, "...................................................~~...............................................");
        map.put(2, "..................................................~~................................................");
        map.put(3, "......@.@......................................~~~..................................................");
        map.put(4, "......@@@~~~..................................~~...........................@.@......................");
        map.put(5, "........@..~~~..............................~~~~~~..........................@@@.......~~............");
        map.put(6, ".............~~...........................@@~...~~~.................................~~~~............");
        map.put(7, "..............~~.........~~~~~~@@.......@@~.......~~.......@........................~~~......~~@....");
        map.put(8, "...............~~~~.....~~~...~~@@@@..@@@~.........~~........................................~@.....");
        map.put(9, "..................~~....~~......~~@@@@@@~...........~~..............................................");
        map.put(10, "..................~~~~~~..........~~~~~~.............~~.............................................");
        map.put(11, "..................~~..................................~~............................................");
        map.put(12, ".................~~....................................~~.........................@@@@..............");
        map.put(13, "................~~......................................~~~~~~...................@@@@@@.............");
        map.put(14, "...@@..........~~............................................~~.................~@@@@@@.............");
        map.put(15, "...............~~........@....................................~~.............~~~~@@@@...............");
        map.put(16, "..............~~.........@@@................@@.................~~...........~~......................");
        map.put(17, ".............~~...........@@@..............@@@..................~~........~~........................");
        map.put(18, "............~~............@@@....................................~~....~~~..........................");
        map.put(19, "............~~....................................................~~~~~.............................");
        map.put(20, "............~~......................................................................................");

        creature = new HashMap<>();
        creature.put(NAME, "Wolf");
        creature.put(LEGEND, "*olf");
        creature.put(SYMBOL, "W");
        creature.put(MAX_WEIGHT, "50");
        creature.put(WEIGHT_LOSS, "5");
        creature.put(MAX_IN_TITLE, "30");
        creature.put(SPEED, "3");
        creature.put(WEIGHT_FOR_FULL, "8");
        creature.put(MODE_OF_NUTRITION, CARNIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_6, "10");
        creature.put(EAT_PROBABILITY_FAMILY_ID_7, "15");
        creature.put(EAT_PROBABILITY_FAMILY_ID_8, "60");
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "80");
        creature.put(EAT_PROBABILITY_FAMILY_ID_10, "60");
        creature.put(EAT_PROBABILITY_FAMILY_ID_11, "70");
        creature.put(EAT_PROBABILITY_FAMILY_ID_12, "15");
        creature.put(EAT_PROBABILITY_FAMILY_ID_13, "10");
        creature.put(EAT_PROBABILITY_FAMILY_ID_14, "40");
        // FamilyId
        settings.put(1, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Anaconda");
        creature.put(LEGEND, "*naconda");
        creature.put(SYMBOL, "A");
        creature.put(MAX_WEIGHT, "15");
        creature.put(WEIGHT_LOSS, "1.5");
        creature.put(MAX_IN_TITLE, "30");
        creature.put(SPEED, "1");
        creature.put(WEIGHT_FOR_FULL, "3");
        creature.put(MODE_OF_NUTRITION, CARNIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_3, "15");
        creature.put(EAT_PROBABILITY_FAMILY_ID_8, "20");
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "40");
        creature.put(EAT_PROBABILITY_FAMILY_ID_14, "10");
        // FamilyId
        settings.put(2, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Fox");
        creature.put(LEGEND, "*ox");
        creature.put(SYMBOL, "F");
        creature.put(MAX_WEIGHT, "8");
        creature.put(WEIGHT_LOSS, "0.8");
        creature.put(MAX_IN_TITLE, "30");
        creature.put(SPEED, "2");
        creature.put(WEIGHT_FOR_FULL, "2");
        creature.put(MODE_OF_NUTRITION, CARNIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_8, "70");
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_14, "60");
        creature.put(EAT_PROBABILITY_FAMILY_ID_15, "40");
        // FamilyId
        settings.put(3, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Bear");
        creature.put(LEGEND, "*ear");
        creature.put(SYMBOL, "B");
        creature.put(MAX_WEIGHT, "500");
        creature.put(WEIGHT_LOSS, "50");
        creature.put(MAX_IN_TITLE, "5");
        creature.put(SPEED, "2");
        creature.put(WEIGHT_FOR_FULL, "80");
        creature.put(MODE_OF_NUTRITION, CARNIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_2, "80");
        creature.put(EAT_PROBABILITY_FAMILY_ID_6, "40");
        creature.put(EAT_PROBABILITY_FAMILY_ID_7, "80");
        creature.put(EAT_PROBABILITY_FAMILY_ID_8, "80");
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_10, "70");
        creature.put(EAT_PROBABILITY_FAMILY_ID_11, "70");
        creature.put(EAT_PROBABILITY_FAMILY_ID_12, "50");
        creature.put(EAT_PROBABILITY_FAMILY_ID_13, "20");
        creature.put(EAT_PROBABILITY_FAMILY_ID_14, "10");
        // FamilyId
        settings.put(4, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Eagle");
        creature.put(LEGEND, "*agle");
        creature.put(SYMBOL, "E");
        creature.put(MAX_WEIGHT, "6");
        creature.put(WEIGHT_LOSS, "0.6");
        creature.put(MAX_IN_TITLE, "20");
        creature.put(SPEED, "3");
        creature.put(WEIGHT_FOR_FULL, "1");
        creature.put(MODE_OF_NUTRITION, CARNIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_3, "10");
        creature.put(EAT_PROBABILITY_FAMILY_ID_8, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_14, "80");
        // FamilyId
        settings.put(5, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Horse");
        creature.put(LEGEND, "*orse");
        creature.put(SYMBOL, "H");
        creature.put(MAX_WEIGHT, "400");
        creature.put(WEIGHT_LOSS, "40");
        creature.put(MAX_IN_TITLE, "20");
        creature.put(SPEED, "4");
        creature.put(WEIGHT_FOR_FULL, "60");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(6, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Deer");
        creature.put(LEGEND, "*eer");
        creature.put(SYMBOL, "D");
        creature.put(MAX_WEIGHT, "300");
        creature.put(WEIGHT_LOSS, "30");
        creature.put(MAX_IN_TITLE, "20");
        creature.put(SPEED, "4");
        creature.put(WEIGHT_FOR_FULL, "50");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(7, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Rabbit");
        creature.put(LEGEND, "*abbit");
        creature.put(SYMBOL, "R");
        creature.put(MAX_WEIGHT, "2");
        creature.put(WEIGHT_LOSS, "0.2");
        creature.put(MAX_IN_TITLE, "150");
        creature.put(SPEED, "2");
        creature.put(WEIGHT_FOR_FULL, "0.45");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(8, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Mouse");
        creature.put(LEGEND, "*ouse");
        creature.put(SYMBOL, "M");
        creature.put(MAX_WEIGHT, "0.05");
        creature.put(WEIGHT_LOSS, "0.005");
        creature.put(MAX_IN_TITLE, "500");
        creature.put(SPEED, "1");
        creature.put(WEIGHT_FOR_FULL, "0.01");
        creature.put(MODE_OF_NUTRITION, OMNIVOROUS);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_15, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(9, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Goat");
        creature.put(LEGEND, "*oat");
        creature.put(SYMBOL, "G");
        creature.put(MAX_WEIGHT, "60");
        creature.put(WEIGHT_LOSS, "6");
        creature.put(MAX_IN_TITLE, "140");
        creature.put(SPEED, "3");
        creature.put(WEIGHT_FOR_FULL, "10");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(10, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Sheep");
        creature.put(LEGEND, "*heep");
        creature.put(SYMBOL, "S");
        creature.put(MAX_WEIGHT, "70");
        creature.put(WEIGHT_LOSS, "7");
        creature.put(MAX_IN_TITLE, "140");
        creature.put(SPEED, "3");
        creature.put(WEIGHT_FOR_FULL, "15");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(11, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Hog");
        creature.put(LEGEND, "h*g");
        creature.put(SYMBOL, "O");
        creature.put(MAX_WEIGHT, "400");
        creature.put(WEIGHT_LOSS, "40");
        creature.put(MAX_IN_TITLE, "50");
        creature.put(SPEED, "2");
        creature.put(WEIGHT_FOR_FULL, "50");
        creature.put(MODE_OF_NUTRITION, OMNIVOROUS);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_9, "50");
        creature.put(EAT_PROBABILITY_FAMILY_ID_15, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(12, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Buffalo");
        creature.put(LEGEND, "b*ffalo");
        creature.put(SYMBOL, "U");
        creature.put(MAX_WEIGHT, "700");
        creature.put(WEIGHT_LOSS, "70");
        creature.put(MAX_IN_TITLE, "10");
        creature.put(SPEED, "4");
        creature.put(WEIGHT_FOR_FULL, "0.15");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(13, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Duck");
        creature.put(LEGEND, "duc*");
        creature.put(SYMBOL, "K");
        creature.put(MAX_WEIGHT, "1");
        creature.put(WEIGHT_LOSS, "0.1");
        creature.put(MAX_IN_TITLE, "200");
        creature.put(SPEED, "4");
        creature.put(WEIGHT_FOR_FULL, "0.15");
        creature.put(MODE_OF_NUTRITION, OMNIVOROUS);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_15, "90");
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(14, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Caterpillar");
        creature.put(LEGEND, "*aterpillar");
        creature.put(SYMBOL, "C");
        creature.put(MAX_WEIGHT, "0.01");
        creature.put(WEIGHT_LOSS, "0.001");
        creature.put(MAX_IN_TITLE, "1000");
        creature.put(MODE_OF_NUTRITION, HERBIVORES);
        creature.put(SHOW_ON_MAP, TRUE);
        creature.put(EAT_PROBABILITY_FAMILY_ID_16, "100");
        // FamilyId
        settings.put(15, creature);

        creature = new HashMap<>();
        creature.put(NAME, "Plants");
        creature.put(LEGEND, "*lants");
        creature.put(SYMBOL, "P");
        creature.put(MAX_WEIGHT, "200");
        creature.put(MAX_IN_TITLE, "1000");
        // FamilyId
        settings.put(16, creature);


    }

    public GlobalSettings loadSettingFile(String fileName) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File(fileName), GlobalSettings.class);
    }

    public void loadFamilyIdSettingsList(List<FamilyIdSettings> familyIdSettingsList, World world) {
        // Default Setting
        for (Map.Entry<Integer, HashMap<String, String>> entryGlobalSettings : this.settings.entrySet()) {
            int familyIdNumber = entryGlobalSettings.getKey();
            HashMap<String, String> valuesHash = entryGlobalSettings.getValue();

            String name = valuesHash.getOrDefault(NAME, "Unknown");
            String legend = valuesHash.getOrDefault(LEGEND, "u*known");
            String symbol = valuesHash.getOrDefault(SYMBOL, "U");
            double maxWeight = Double.parseDouble(valuesHash.getOrDefault(MAX_WEIGHT, "1"));
            double weightLoss = Double.parseDouble(valuesHash.getOrDefault(WEIGHT_LOSS, "0"));
            int maxInTitle = Integer.parseInt(valuesHash.getOrDefault(MAX_IN_TITLE, "1"));
            int speed = Integer.parseInt(valuesHash.getOrDefault(SPEED, "0"));
            FoodChain foodChain = FoodChain.valueOf(valuesHash.getOrDefault(MODE_OF_NUTRITION, "NONE"));
            boolean isShowOnMap = Boolean.parseBoolean(valuesHash.getOrDefault(SHOW_ON_MAP, "false"));

            FamilyIdSettings currentFamilyIdSettings = new FamilyIdSettings(familyIdNumber, world, name, legend, symbol,
                    maxWeight, maxInTitle, speed, foodChain, isShowOnMap, weightLoss);
            familyIdSettingsList.add(currentFamilyIdSettings);
        }

        // EatProbability
        for (Map.Entry<Integer, HashMap<String, String>> entryGlobalSettings : this.settings.entrySet()) {
            int familyIdNumber = entryGlobalSettings.getKey();
            HashMap<String, String> valuesHash = entryGlobalSettings.getValue();

            FamilyIdSettings currentFamilyIdSettings = getFamilyIdSettings(world, familyIdNumber);

            Map<Integer, Integer> numberEatProbabilityList = valuesHash.entrySet().stream()
                    .filter(element -> element.getKey().startsWith("EatProbability_FamilyId"))
                    .collect(Collectors.toMap(key -> Integer.parseInt(key.getKey()
                            .replace("EatProbability_FamilyId_", "")),
                            value -> Integer.parseInt(value.getValue())));
            for (Map.Entry<Integer, Integer> entry : numberEatProbabilityList.entrySet()) {
                int idNumber = entry.getKey();
                int probability = entry.getValue();
                if (probability <= 0) {
                    throw new GlobalSettingsException("EatProbability value is 0. familyIdNumber: "
                            + familyIdNumber + ", idNumber: " + idNumber + ", probability: " + probability);
                }
                FamilyIdSettings probabilityFamilyIdSettings = getFamilyIdSettings(world, idNumber);
                currentFamilyIdSettings.addElementToEatProbabilityList(probabilityFamilyIdSettings, probability);
            }
        }
    }

    private FamilyIdSettings getFamilyIdSettings(World world, int idNumber) {
        Optional<FamilyIdSettings> optionalFamilyIdSettings = world.getFamilyIdSettingsList().stream()
                .filter(idSettings -> idSettings.getFamilyIdNumber() == idNumber)
                .findFirst();
        return optionalFamilyIdSettings.orElseThrow();
    }


    public void createMapLegendRevers() {
        for (Map.Entry<MapLegend, String> mapLegendStringEntry : mapLegend.entrySet()) {
            mapLegendRevers.put(mapLegendStringEntry.getValue(), mapLegendStringEntry.getKey());
        }
    }

    public int getMapSizeWidth() {
        return mapSizeWidth;
    }

    public int getMapSizeHeight() {
        return mapSizeHeight;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public Map<MapLegend, String> getMapLegend() {
        return mapLegend;
    }

    public Map<String, MapLegend> getMapLegendRevers() {
        return mapLegendRevers;
    }

    public Map<MapLegend, ColorCode> getMapColor() {
        return mapColor;
    }

    public ColorCode getMapColorBackGround() {
        return mapColorBackGround;
    }

    public Map<LineCode, String> getStatisticLine() {
        return statisticLine;
    }

    public int getStatisticSizeOfNames() {
        return statisticSizeOfNames;
    }

    public ColorCode getStatisticNameColorPredator() {
        return statisticNameColorPredator;
    }

    public ColorCode getStatisticNameColorHerbivore() {
        return statisticNameColorHerbivore;
    }

    public ColorCode getStatisticNameColorOmnivorous() {
        return statisticNameColorOmnivorous;
    }

    public ColorCode getStatisticNameColorLine() {
        return statisticNameColorLine;
    }

    public ColorCode getStatisticNameColorUnknown() {
        return statisticNameColorUnknown;
    }

    public String getStatisticNameSymbol() {
        return statisticNameSymbol;
    }

    public ColorCode getStatisticNumbersColor() {
        return statisticNumbersColor;
    }

    public ColorCode getStatisticNumbersPositiveColor() {
        return statisticNumbersPositiveColor;
    }

    public ColorCode getStatisticNumbersNegativeColor() {
        return statisticNumbersNegativeColor;
    }

    public int getStatisticSizeFrameAfterNames() {
        return statisticSizeFrameAfterNames;
    }

    public ColorCode getStatisticNameColorTurn() {
        return statisticNameColorTurn;
    }

    public int getReproductionChance() {
        return reproductionChance;
    }

    @SuppressWarnings("unused")
    public Map<Integer, HashMap<String, String>> getSettings() {
        return settings;
    }
}
