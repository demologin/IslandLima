package com.javarush.island.osypenko.pref;

import com.javarush.island.osypenko.constants.CharacteristicsEntities;
import com.javarush.island.osypenko.entities.Organism;
import com.javarush.island.osypenko.entities.animal.carnivores.*;
import com.javarush.island.osypenko.entities.animal.herbivores.*;
import com.javarush.island.osypenko.entities.plant.grass.Grass;

public class FactoryOrganism {
    public static Organism getOrganism(EntitiesType entitiesType) {
        return switch (entitiesType) {
            case BEAR -> new Bear(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BEAR));
            case EAGLE -> new Eagle(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.EAGLE));
            case FOX -> new Fox(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.FOX));
            case SNAKE -> new Snake(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.SNAKE));
            case WOLF -> new Wolf(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.WOLF));
            case BOAR -> new Boar(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BOAR));
            case BUFFALO -> new Buffalo(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.BUFFALO));
            case CATERPILLAR -> new Caterpillar(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.CATERPILLAR));
            case DEER -> new Deer(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.DEER));
            case DUCK -> new Duck(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.DUCK));
            case GOAT -> new Goat(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.GOAT));
            case HORSE -> new Horse(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.HORSE));
            case MOUSE -> new Mouse(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.MOUSE));
            case RABBIT -> new Rabbit(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.RABBIT));
            case SHEEP -> new Sheep(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.SHEEP));
            case GRASS -> new Grass(CharacteristicsEntities.getCharacteristicsEntities().get(EntitiesType.GRASS));
        };
    }
}
