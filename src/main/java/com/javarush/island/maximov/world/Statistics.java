package com.javarush.island.maximov.world;

import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.GlobalSettings;
import com.javarush.island.maximov.utils.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    public static final DecimalFormat DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance();
    private final World world;
    private final GlobalSettings globalSettings;
    private final Color color;
    private final Logger logger;

    private final List<StringBuilder> stringBuilderList = new ArrayList<>();
    private final List<String> displayNamesStartList = new ArrayList<>();
    private final List<String> displayNamesEndList = new ArrayList<>();

    private int mapHeight;
    private int mapWidth;

    public Statistics(World world, GlobalSettings globalSettings, Color color, Logger logger) {
        this.world = world;
        this.globalSettings = globalSettings;
        this.color = color;
        this.logger = logger;
    }

    public void initialization() {
        mapHeight = globalSettings.getMapSizeHeight() + 2;
        mapWidth = globalSettings.getMapSizeWidth() + 2;
        prepStringBuilderList();
        prepDisplayNames();
        prepDisplayNamesEnd();

        logger.logMessage("Press 'Enter' to start game.", false);
        new Scanner(System.in).nextLine();

    }

    private void prepStringBuilderList() {
        for (int i = 0; i <= mapHeight; i++) {
            stringBuilderList.add(new StringBuilder());
        }
    }

    private void prepDisplayNames() {
        displayNamesStartList.add("First line");
        String backColor = color.selectColor(globalSettings.getMapColorBackGround());
        String fontColor;

        displayNamesStartList.add(backColor + "  " + color.selectColor(globalSettings.getStatisticNameColorLine())
                + globalSettings.getStatisticLine().get(LineCode.LINE_DOWN_RIGHT)
                + globalSettings.getStatisticLine().get(LineCode.LINE_HORIZONTAL)
                .repeat(globalSettings.getStatisticSizeOfNames() + 2)
                + globalSettings.getStatisticLine().get(LineCode.LINE_VERTICAL_LEFT)
        );

        int indexForFamilyId;
        for (int currentHeight = 2; currentHeight <= mapHeight - 1; currentHeight++) {
            String finalLine = "  " + color.selectColor(globalSettings.getStatisticNameColorLine());
            finalLine += globalSettings.getStatisticLine().get(LineCode.LINE_VERTICAL);

            indexForFamilyId = (currentHeight - 2);
            if (indexForFamilyId < world.getFamilyIdSettingsList().size()) {
                FamilyIdSettings familyIdSettings = world.getFamilyIdSettingsList().get(indexForFamilyId);
                String legend = familyIdSettings.getLegend();
                if (legend.length() > globalSettings.getStatisticSizeOfNames()) {
                    legend = legend.substring(0, globalSettings.getStatisticSizeOfNames());
                } else {
                    legend += " ".repeat(globalSettings.getStatisticSizeOfNames() - legend.length());
                }

                fontColor = color.selectColor(
                        switch (familyIdSettings.getModeOfNutrition()) {
                            case OMNIVOROUS -> globalSettings.getStatisticNameColorOmnivorous();
                            case CARNIVORES -> globalSettings.getStatisticNameColorPredator();
                            case HERBIVORES -> globalSettings.getStatisticNameColorHerbivore();
                            case NONE -> globalSettings.getStatisticNameColorUnknown();
                        });
                String symbol = familyIdSettings.getSymbol();
                String beforeSymbol = legend.substring(0, legend.indexOf(globalSettings.getStatisticNameSymbol()));
                String afterSymbol = legend.substring(
                        legend.indexOf(globalSettings.getStatisticNameSymbol()) + 1);

                String formatName = fontColor + beforeSymbol + color.selectColor(ColorCode.BOLD)
                        + symbol + color.selectColor(ColorCode.RESET) + backColor + fontColor + afterSymbol;

                finalLine += " " + formatName;
            }
            displayNamesStartList.add(backColor + finalLine);
        }
        displayNamesStartList.add(backColor + "  " + color.selectColor(globalSettings.getStatisticNameColorLine())
                + globalSettings.getStatisticLine().get(LineCode.LINE_UP_RIGHT)
                + globalSettings.getStatisticLine().get(LineCode.LINE_HORIZONTAL)
                .repeat(globalSettings.getStatisticSizeOfNames() - 3)
                + globalSettings.getStatisticLine().get(LineCode.LINE_VERTICAL_LEFT)
        );
    }

    private void addStatsForDisplay(StringBuilder stringBuilder, int currentHeight) {
        String backColor = color.selectColor(globalSettings.getMapColorBackGround());

        if (currentHeight == 1 || currentHeight == mapHeight) {
            String fontColor = color.selectColor(globalSettings.getStatisticNameColorLine());
            int number = (currentHeight == 1) ? world.getTurn().get() : world.getTotalLiveCreatures();
            int numberLength = DivideAndConquer.calculateNumbersIfInteger(number);
            int symbolDiff = globalSettings.getStatisticSizeFrameAfterNames() - numberLength - 6;
            stringBuilder
                    .append(backColor)
                    .append(color.selectColor(globalSettings.getStatisticNameColorTurn()))
                    .append(color.selectColor(ColorCode.BOLD))
                    .append((currentHeight == 1) ? " Turn " : " Creatures ")
                    .append(DECIMAL_FORMAT.format(number))
                    .append(color.selectColor(ColorCode.RESET))
                    .append(backColor)
                    .append(fontColor)
                    .append(" ")
                    .append(globalSettings.getStatisticLine().get(LineCode.LINE_VERTICAL_RIGHT))
                    .append(globalSettings.getStatisticLine().get(LineCode.LINE_HORIZONTAL)
                            .repeat(symbolDiff));

        } else {
            stringBuilder.append(backColor);
            int indexForFamilyId = (currentHeight - 2);
            if (indexForFamilyId < world.getFamilyIdSettingsList().size()) {
                FamilyIdSettings familyIdSettings = world.getFamilyIdSettingsList().get(indexForFamilyId);
                int originalNumber = world.getOriginalNumbersOfAnimals().get(familyIdSettings);
                int currentNumber = world.getWorldNumbersOfAnimals().get(familyIdSettings).get();
                int diffNumber = currentNumber - originalNumber;
                if (diffNumber < 0) {
                    stringBuilder.append(color.selectColor(globalSettings.getStatisticNumbersNegativeColor()))
                            .append("-");
                } else {
                    stringBuilder.append(color.selectColor(globalSettings.getStatisticNumbersPositiveColor()))
                            .append("+");
                }
                diffNumber = Math.abs(diffNumber);
                stringBuilder
                        .append(DECIMAL_FORMAT.format(diffNumber))
                        .append(color.selectColor(globalSettings.getStatisticNumbersColor()))
                        .append(" (").append(DECIMAL_FORMAT.format(currentNumber)).append(")");

                int diffNumberLength = DivideAndConquer.calculateNumbersIfInteger(diffNumber);
                int currentNumberLength = DivideAndConquer.calculateNumbersIfInteger(currentNumber);
                int symbolDiff = globalSettings.getStatisticSizeFrameAfterNames() - diffNumberLength
                        - currentNumberLength;
                stringBuilder.append(" ".repeat(Math.max(0, symbolDiff)));
            } else {
                stringBuilder.append(" ".repeat(Math.max(0, (globalSettings.getStatisticSizeOfNames()
                        + globalSettings.getStatisticSizeFrameAfterNames() + 5))));
            }
        }
    }

    private void prepDisplayNamesEnd() {
        displayNamesEndList.add("First line");
        String backColor = color.selectColor(globalSettings.getMapColorBackGround());
        String fontColor = color.selectColor(globalSettings.getStatisticNameColorLine());

        displayNamesEndList.add(backColor + fontColor
                + globalSettings.getStatisticLine().get(LineCode.LINE_DOWN_LEFT));

        for (int currentHeight = 2; currentHeight <= mapHeight - 1; currentHeight++) {
            displayNamesEndList.add(backColor + fontColor
                    + globalSettings.getStatisticLine().get(LineCode.LINE_VERTICAL));
        }
        displayNamesEndList.add(backColor + fontColor + globalSettings.getStatisticLine().get(LineCode.LINE_UP_LEFT));
    }

    public void display() {
        StringBuilder stringBuilder;
        for (int currentHeight = 1; currentHeight <= mapHeight; currentHeight++) {
            stringBuilder = stringBuilderList.get(currentHeight);
            addMapForDisplay(stringBuilder, currentHeight);
            addNamesForDisplay(stringBuilder, currentHeight);
            addStatsForDisplay(stringBuilder, currentHeight);
            addNamesEndForDisplay(stringBuilder, currentHeight);
            logger.displayMessageStringBuilder(stringBuilder);
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

    private void addMapForDisplay(StringBuilder stringBuilder, int currentHeight) {
        int maxTile = currentHeight * mapWidth;
        int tileIndex = maxTile - mapWidth + 1;
        int turn = world.getTurn().get();
        stringBuilder.append(color.selectColor(globalSettings.getMapColorBackGround()));
        for (; tileIndex <= maxTile; tileIndex++) {
            Tile tile = world.getMapList().get(tileIndex);

            Optional<Map.Entry<FamilyIdSettings, AtomicInteger>> maxFamilyIdSettingsAtomicEntry
                    = tile.getNumbersOfAnimals().entrySet().stream()
                    .filter(entry -> entry.getKey().isShowOnMap())
                    .max(Comparator.comparingInt(entry -> entry.getValue().get()));

            if (turn > 0 && maxFamilyIdSettingsAtomicEntry.isPresent()
                    && maxFamilyIdSettingsAtomicEntry.get().getValue().get() > 0) {
                stringBuilder.append(
                                color.selectColor(
                                        switch (maxFamilyIdSettingsAtomicEntry.get().getKey().getModeOfNutrition()) {
                                            case OMNIVOROUS -> globalSettings.getStatisticNameColorOmnivorous();
                                            case CARNIVORES -> globalSettings.getStatisticNameColorPredator();
                                            case HERBIVORES -> globalSettings.getStatisticNameColorHerbivore();
                                            case NONE -> globalSettings.getStatisticNameColorUnknown();
                                        }))
                        .append(maxFamilyIdSettingsAtomicEntry.get().getKey().getSymbol());
            } else {
                stringBuilder.append(color.selectColor(tile.getColorCode())).append(tile.getSymbol());
            }
        }
    }


    private void addNamesForDisplay(StringBuilder stringBuilder, int index) {
        stringBuilder.append(displayNamesStartList.get(index));
    }

    private void addNamesEndForDisplay(StringBuilder stringBuilder, int index) {
        stringBuilder.append(displayNamesEndList.get(index));
    }
}
