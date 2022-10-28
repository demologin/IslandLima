package com.javarush.island.marzhiievskyi.utils;

public class Constants {
    //path to yaml files
    public static final String ORGANISM_PARAMETERS_FILE_PATH = "src/main/resources/OrganismsParameters.yaml";
    public static final String GAME_ISLAND_PARAMETERS_FILE_PATH = "src/main/resources/ParametersOfIsland.yaml";
    public static final String ORGANISM_EAT_CHANCE_FILE_PATH = "src/main/resources/CanEat.yaml";

    //setting for organism
    public static final double WEIGHT_LOSE_PER_ACTION = 1;
    public static final int CHANCE_TO_BIRTH_CHILD = 20;
    public static final int CHANCE_TO_BIRTH_CHILD_FOR_PLANTS = 3;
    public static final int COUNT_OF_DESCENDANTS_FOR_PLANTS = 4;
    public static final int COUNT_OF_DESCENDANTS_FOR_ANIMALS = 1;

    //some text
    public static final String WELCOME_MSG = "Starter position and count of all organisms. Good luck =)";
    public static final String BETWEEN_INIT_AND_ALL_TICKS = "   ==========================================  ";
    public static final String TOTAL_ORGANISMS = "Total organisms on the island: ";

}
