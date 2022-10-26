package com.javarush.island.sternard.services;

import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.view.printToConsole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowStatisticsService {
    private final Controller controller;

    public Runnable showStatisticsServiceStart() {
        return () ->
        {
            controller.getDAY_NUMBER().getAndIncrement();
            new printToConsole(controller).printStatistic();
        };
    }
}
