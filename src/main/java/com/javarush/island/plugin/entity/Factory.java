package com.javarush.island.plugin.entity;

import com.javarush.island.plugin.entity.carnivores.*;
import com.javarush.island.plugin.entity.herbivores.*;
import com.javarush.island.plugin.gameset.GamePreferences;

public class Factory {
    static Factory instance;

    private Factory() {
    }

    public static Factory getInstance(){
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Animal getAlive(Organism organism) {
        Animal animal =
                switch (organism){
                    case PLANT -> new Plant(GamePreferences.getInstance().getMap().get(Organism.PLANT));
                    case BEAR -> new Bear(GamePreferences.getInstance().getMap().get(Organism.BEAR));
                    case EAGLE-> new Eagle(GamePreferences.getInstance().getMap().get(Organism.EAGLE));
                    case FOX -> new Fox(GamePreferences.getInstance().getMap().get(Organism.FOX));
                    case SNAKE -> new Snake(GamePreferences.getInstance().getMap().get(Organism.SNAKE));
                    case WOLF -> new Wolf(GamePreferences.getInstance().getMap().get(Organism.WOLF));
                    case CATERPILLAR -> new Caterpillar(GamePreferences.getInstance().getMap().get(Organism.CATERPILLAR));
                    case COW -> new Cow(GamePreferences.getInstance().getMap().get(Organism.COW));
                    case DEER -> new Deer(GamePreferences.getInstance().getMap().get(Organism.DEER));
                    case DUCK -> new Duck(GamePreferences.getInstance().getMap().get(Organism.DUCK));
                    case MOUSE -> new Mouse(GamePreferences.getInstance().getMap().get(Organism.MOUSE));
                    case HORSE -> new Horse(GamePreferences.getInstance().getMap().get(Organism.HORSE));
                    case RABBIT -> new Rabbit(GamePreferences.getInstance().getMap().get(Organism.RABBIT));
                    case SHEEP -> new Sheep(GamePreferences.getInstance().getMap().get(Organism.SHEEP));
                };

        return animal;
    }
}

