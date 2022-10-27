package com.javarush.island.marzhiievskyi.services.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.marzhiievskyi.entity.field.GameField;
import com.javarush.island.marzhiievskyi.utils.Constants;
import com.javarush.island.marzhiievskyi.utils.gettingParameters.GettingParametersOfIsland;
import com.javarush.island.marzhiievskyi.wiev.ConsoleGUI;

import java.io.File;
import java.io.IOException;

public class IslandFactory {
    private final GameField gameField;
    private final GettingParametersOfIsland gettingParametersOfIsland;

    public IslandFactory() {
        ObjectMapper mapper = new YAMLMapper();
        try {
            gettingParametersOfIsland = mapper.readValue(new File(Constants.GAME_ISLAND_PARAMETERS_FILE_PATH), GettingParametersOfIsland.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameField = GameField.getGameField(gettingParametersOfIsland.getRows(), gettingParametersOfIsland.getColumns());
        gameField.initField();
        System.out.println(Constants.WELCOME_MSG);
        ConsoleGUI consoleGUI = new ConsoleGUI();
        consoleGUI.printField(getGameField());
        consoleGUI.printStatistic(getGameField());
        System.out.println(Constants.BETWEEN_INIT_AND_ALL_TICKS);
    }

    public GameField getGameField() {
        return gameField;
    }

    public GettingParametersOfIsland getParametersOfIsland() {
        return gettingParametersOfIsland;
    }
}
