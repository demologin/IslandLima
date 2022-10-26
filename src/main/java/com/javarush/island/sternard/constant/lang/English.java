package com.javarush.island.sternard.constant.lang;

public interface English {
    String FILE_ERROR = "Perhaps the file is not found or you are not right for reading this file: ";
    String ORGANISM_CREATION_ERROR = "An error of creating an organism";
    String ACCESS_FIELD_ERROR = "Field access error";
    String FIELD_IS_EMPTY = "Field is empty. Add the value, please.";
    String VALUE_MUST_BE = "Value must be ";
    String NOT_NULL_AND_NOT_EMPTY = VALUE_MUST_BE + "not null and not empty.";
    String INVALID_FILE_PATH = "Invalid file path.";
    String DIED_ORGANISMS_COUNT = "Dead organisms number";
    String DIED_ORGANISMS_ENUMERATION = "Dead organisms";
    String PLANTS = "Plants";
    String ANIMALS = "Animals";
    String CARNIVORES = "Carnivores";
    String HERBIVORES = "Herbivores";
    String ORGANISMS = "Organisms";
    String DAY_NUMBER = "--== DAY %3d ==--";
    String GAME_IS_INITIALIZE_WAIT = "Please wait, the game is initialize...";
    String INIT_TIME_TEXT = "Init time";
    String ERROR_WITH_ACTION = "Error with action";
    String SIMULATION_END = "Simulation is stopped! Simulation time: ";
    String INIT_GAME_START_WITH_ORGANISMS_IN_CELL = "initGame start with {} organisms in cell";
    String THE_GAME_WAS_EMERGENCY_STOPPED = "The game was emergency stopped, the simulation was not finished";
    String INCORRECT_TYPES_OF_ORGANISMS_LOG = "An attempt to use incorrect types of organisms";
    String INCORRECT_TYPES_OF_ORGANISMS = "plant & animal are mandatory types of organisms, add that to settings file";
    String NO_SUCH_COLOR_IN_CONSOLE_COLORS = "There are no such color in ConsoleColors";

}