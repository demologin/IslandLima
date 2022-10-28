package com.javarush.island.uzienko.view;

import com.javarush.island.uzienko.config.GameConfig;
import com.javarush.island.uzienko.sevices.StatisticService;
import com.javarush.island.uzienko.storage.Coords;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConsoleGameView implements GameView {
    private final StatisticService statisticController;
    private final GameConfig config;

    public ConsoleGameView(GameConfig config, StatisticService statisticController) {
        this.config = config;
        this.statisticController = statisticController;
    }

    @Override
    public void showMap() {
        System.out.println(mapToString());
    }

    @Override
    public void showStat() {
        System.out.println(statToString());
    }

    private String mapToString() {
        Map<Coords, List<String>> nodes = statisticController.getMapStatistic();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < config.getRows(); i++) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("|");
            for (int j = 0; j < config.getCols(); j++) {
                Coords coords = new Coords(j, i);
                List<String> residents = nodes.get(coords);
                for (int k = 0; k < config.getAnimalInCell(); k++) {
                    if (k < residents.size()) {
                        stringBuilder.append(config.getContext().get(residents.get(k)).getProperties().getIcon());
                    } else {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append("|");
            }
        }
        return stringBuilder.toString();
    }

    private String statToString() {
        Map<String, Integer> statistic = statisticController.getOverallStatistic();
        StringBuilder stringBuilder = new StringBuilder(System.lineSeparator());
        statistic.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> stringBuilder.append
                        (String.format("%s %d; ",
                                config.getContext().get(entry.getKey()).getProperties().getIcon(),
                                entry.getValue())));
        return stringBuilder.toString();
    }
}