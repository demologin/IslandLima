package com.javarush.island.alimin.entity.ecosystem;

import com.javarush.island.alimin.entity.ecosystem.animals.herbivores.*;
import com.javarush.island.alimin.entity.ecosystem.animals.predators.*;
import com.javarush.island.alimin.entity.ecosystem.plants.Plant;
import com.javarush.island.alimin.properties.OrganismParameters;

public class OrganismFactory {

    private static volatile OrganismFactory instance;

    private OrganismFactory() {

    }

    public static OrganismFactory getInstance() {
        OrganismFactory result = instance;
        if (result != null) {
            return result;
        }
        synchronized (OrganismFactory.class) {
            if (instance == null) {
                instance = new OrganismFactory();
            }
            return instance;
        }
    }

    public Organism createOrganism(Types type, OrganismParameters parameters) {
        return switch (type) {
            case WOLF -> new Wolf(parameters);
            case SNAKE -> new Snake(parameters);
            case FOX -> new Fox(parameters);
            case BEAR -> new Bear(parameters);
            case EAGLE -> new Eagle(parameters);
            case HORSE -> new Horse(parameters);
            case DEER -> new Deer(parameters);
            case RABBIT -> new Rabbit(parameters);
            case MOUSE -> new Mouse(parameters);
            case GOAT -> new Goat(parameters);
            case SHEEP -> new Sheep(parameters);
            case BOAR -> new Boar(parameters);
            case BUFFALO -> new Buffalo(parameters);
            case DUCK -> new Duck(parameters);
            case CATERPILLAR -> new Caterpillar(parameters);
            case PLANT -> new Plant(parameters);
        };
    }
}
