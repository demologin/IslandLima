package com.javarush.island.bogdan.initializer;

import com.javarush.island.bogdan.island.Island;

public class Initializer {
    public Island createIsland(){
        IslandCreator islandCreator = new IslandCreator();
        return islandCreator.create();
    }
}
