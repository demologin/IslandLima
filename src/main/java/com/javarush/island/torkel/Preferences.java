package com.javarush.island.torkel;

import com.javarush.island.torkel.entity.animal.herbivore.*;
import com.javarush.island.torkel.entity.animal.predator.*;
import com.javarush.island.torkel.entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.lang.Class;

public class Preferences {
    private static Preferences instance = null;

    private Preferences() {
        Map<Class, Integer> mapWolf = new HashMap<>();
        mapWolf.put(Horse.class, 10);
        mapWolf.put(Deer.class, 15);
        mapWolf.put(Rabbit.class, 60);
        mapWolf.put(Mouse.class, 80);
        mapWolf.put(Goat.class, 60);
        mapWolf.put(Sheep.class, 70);
        mapWolf.put(Boar.class, 15);
        mapWolf.put(Buffalo.class, 10);
        mapWolf.put(Duck.class, 40);
        mapProbability.put(Wolf.class, mapWolf);

        Map<Class, Integer> mapBoa = new HashMap<>();
        mapBoa.put(Fox.class, 15);
        mapBoa.put(Rabbit.class, 20);
        mapBoa.put(Mouse.class, 40);
        mapBoa.put(Duck.class, 10);
        mapProbability.put(Boar.class, mapBoa);

        Map<Class, Integer> mapFox = new HashMap<>();
        mapFox.put(Rabbit.class, 70);
        mapFox.put(Mouse.class, 90);
        mapFox.put(Duck.class, 60);
        mapFox.put(Caterpillar.class, 40);
        mapProbability.put(Fox.class, mapFox);

        Map<Class, Integer> mapBear = new HashMap<>();
        mapBear.put(Boa.class, 80);
        mapBear.put(Horse.class, 40);
        mapBear.put(Deer.class, 80);
        mapBear.put(Rabbit.class, 80);
        mapBear.put(Mouse.class, 90);
        mapBear.put(Goat.class, 70);
        mapBear.put(Sheep.class, 70);
        mapBear.put(Boar.class, 50);
        mapBear.put(Buffalo.class, 20);
        mapBear.put(Duck.class, 10);
        mapProbability.put(Bear.class, mapBear);

        Map<Class, Integer> mapEagle = new HashMap<>();
        mapEagle.put(Fox.class, 10);
        mapEagle.put(Rabbit.class, 90);
        mapEagle.put(Mouse.class, 90);
        mapEagle.put(Duck.class, 80);
        mapProbability.put(Eagle.class, mapEagle);
        Map<Class, Integer> mapHorse = new HashMap<>();
        mapHorse.put(Plant.class, 100);
        mapProbability.put(Horse.class, mapHorse);

        Map<Class, Integer> mapDeer = new HashMap<>();
        mapDeer.put(Plant.class, 100);
        mapProbability.put(Deer.class, mapDeer);

        Map<Class, Integer> mapRabbit = new HashMap<>();
        mapEagle.put(Plant.class, 100);
        mapProbability.put(Rabbit.class, mapRabbit);

        Map<Class, Integer> mapMouse = new HashMap<>();
        mapMouse.put(Caterpillar.class, 90);
        mapMouse.put(Plant.class, 100);
        mapProbability.put(Mouse.class, mapMouse);

        Map<Class, Integer> mapGoat = new HashMap<>();
        mapGoat.put(Plant.class, 100);
        mapProbability.put(Goat.class, mapGoat);

        Map<Class, Integer> mapSheep = new HashMap<>();
        mapSheep.put(Plant.class, 100);
        mapProbability.put(Sheep.class, mapSheep);

        Map<Class, Integer> mapBoar = new HashMap<>();
        mapBoar.put(Mouse.class, 50);
        mapBear.put(Caterpillar.class, 90);
        mapBoar.put(Plant.class, 100);
        mapProbability.put(Boar.class, mapBoar);

        Map<Class, Integer> mapBuffalo = new HashMap<>();
        mapBuffalo.put(Plant.class, 100);
        mapProbability.put(Buffalo.class, mapBuffalo);

        Map<Class, Integer> mapDuck = new HashMap<>();
        mapDuck.put(Caterpillar.class, 90);
        mapDuck.put(Plant.class, 100);
        mapProbability.put(Duck.class, mapDuck);

        Map<Class, Integer> mapCaterpillar = new HashMap<>();
        mapCaterpillar.put(Plant.class, 100);
        mapProbability.put(Caterpillar.class, mapCaterpillar);

    }

    public Map<Class, Map<Class, Integer>> getMapProbability() {
        return mapProbability;
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    Map<Class, Map<Class, Integer>> mapProbability = new HashMap<>();


}
