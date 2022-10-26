package com.javarush.island.sternard.view;

import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.enumeration.ConsoleColors;
import com.javarush.island.sternard.organisms.factory.OrganismFactory;
import com.javarush.island.sternard.organisms.parents.Organism;
import com.javarush.island.sternard.settings.Settings;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.javarush.island.sternard.constant.lang.English.*;

public class printToConsole {
    private final String RESET_ANSI_COLOR = ConsoleColors.RESET.getAnsiColor();
    private final Controller controller;
    private final String textColor = Settings.get().getTextColorStatisticKey();
    private final String drawStatisticTextColor = Settings.get().getStatisticTextColorDay();
    private final String textColorStatisticValue = Settings.get().getTextColorStatisticValue();
    private final int statisticColumns = Settings.get().getStatisticColumns();

    public printToConsole(Controller controller) {
        this.controller = controller;
    }

    public void printStatistic() {
        Map<String, Organism> organismMapFromJson = OrganismFactory.organismMapFromJson();
        Map<String, Integer> statistics = new ConcurrentHashMap<>();
        List<Organism> allOrganisms = new CopyOnWriteArrayList<>();
        controller.statisticCollect(statistics, allOrganisms);
        System.out.printf(" %s" + DAY_NUMBER + "\033[0m \n", drawStatisticTextColor, controller.getDAY_NUMBER().get());
        this.printStatisticTable(organismMapFromJson, statistics);
        this.printStatisticAllOrganismsCount(allOrganisms);
        System.out.println("\n");
    }

    private void printStatisticTable(Map<String, Organism> organismMapFromJson, Map<String, Integer> statistics) {
        int table = 0;
        for (Map.Entry<String, Integer> key : statistics.entrySet()) {
            if (table % statisticColumns == 0)
                System.out.println();

            System.out.printf("%s %s%-10d %s\t", organismMapFromJson.get(key.getKey()).getIcon(),
                    textColorStatisticValue, key.getValue(), RESET_ANSI_COLOR);
            table++;
        }
    }

    private void printStatisticAllOrganismsCount(List<Organism> allOrganisms) {
        long allOrganismCount = allOrganisms.size();
        long herbivoreCount = allOrganisms.stream()
                .filter(o -> Objects.equals(o.getOrganismType(), "herbivore"))
                .count();
        long carnivoreCount = allOrganisms.stream()
                .filter(o -> Objects.equals(o.getOrganismType(), "carnivore"))
                .count();
        long animalsCount = allOrganisms.stream()
                .filter(o -> Objects.equals(o.getOrganismMainType(), "animal"))
                .count();
        long plantsCount = allOrganisms.stream()
                .filter(o -> Objects.equals(o.getOrganismMainType(), "plant"))
                .count();
        int animalsDiedNumber = Controller.getDiedOrganisms()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        printStatisticsToConsole(allOrganismCount, herbivoreCount, carnivoreCount, animalsCount, plantsCount, animalsDiedNumber);
    }

    private void printStatisticsToConsole(long allOrganismCount, long herbivoreCount, long carnivoreCount,
                                          long animalsCount, long plantsCount, int animalsDiedNumber) {
        System.out.println();
        printToConsoleFormat(ORGANISMS, allOrganismCount);
        printToConsoleFormat(HERBIVORES, herbivoreCount);
        printToConsoleFormat(CARNIVORES, carnivoreCount);
        printToConsoleFormat(ANIMALS, animalsCount);
        printToConsoleFormat(PLANTS, plantsCount);
        if (animalsDiedNumber > 0) {
            printToConsoleFormat(DIED_ORGANISMS_COUNT, animalsDiedNumber);
            System.out.print(textColor + DIED_ORGANISMS_ENUMERATION + ": " + RESET_ANSI_COLOR);
            Controller.getDiedOrganisms().entrySet().stream()
                    .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())) // sort by map value
                    .limit(7)
                    .forEach(k -> System.out.print(MessageFormat.format("{0}({1}{2}){3} ",
                            k.getKey(), textColorStatisticValue, k.getValue(), RESET_ANSI_COLOR)));
        }
    }

    private void printToConsoleFormat(String text, long count) {
        System.out.println(MessageFormat.format("{0}{1}: {2}{3}{4}{5}",
                textColor, text, RESET_ANSI_COLOR,
                textColorStatisticValue, count, RESET_ANSI_COLOR));
    }

}