package com.javarush.island.uzienko.sevices;

import com.javarush.island.uzienko.config.GameConfig;
import com.javarush.island.uzienko.repository.Repository;
import com.javarush.island.uzienko.storage.Coords;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticService {
    private final Repository repository;
    private final GameConfig config;

    public StatisticService(GameConfig config, Repository repository) {
        this.repository = repository;
        this.config = config;
    }

    public Map<Coords, List<String>> getMapStatistic() {
        Map<Coords, List<String>> nodes = new HashMap<>();
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getCols(); j++) {
                Coords coords = new Coords(j, i);
                Map<String, Integer> statistic = new HashMap<>();
                repository.getResidentsByCoords(coords).forEach((k, v) -> statistic.merge(k, v.size(), Integer::sum));
                List<String> residents = statistic.entrySet().stream()
                        .filter(item -> item.getValue() > 0)
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .limit(config.getAnimalInCell()).map(Map.Entry::getKey).toList();
                nodes.put(coords, residents);
            }
        }
        return nodes;
    }

    public Map<String, Integer> getOverallStatistic() {
        Map<String, Integer> statistic = new HashMap<>();
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getCols(); j++) {
                Coords coords = new Coords(j, i);
                repository.getResidentsByCoords(coords).forEach((k, v) -> statistic.merge(k, v.size(), Integer::sum));
            }
        }
        return statistic;
    }

    public int getSummary() {
        return getOverallStatistic().values().stream()
                .reduce(0, Integer::sum);
    }
}
