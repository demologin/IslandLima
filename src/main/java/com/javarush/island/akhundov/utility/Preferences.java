package com.javarush.island.akhundov.utility;

import com.javarush.island.akhundov.animals.herbivores.*;
import com.javarush.island.akhundov.animals.predators.*;

import java.util.HashMap;

public class Preferences {
    private final HashMap<Class<?>, HashMap<Class<?>,Integer>> percentageMap = new HashMap<>();
    private final HashMap<Class<?>, String> namesMap = new HashMap<>();

    public Preferences(){
        fillTheMap();
    }

    private void fillTheMap(){
        Boar boar = new Boar();
        HashMap<Class<?>,Integer> boarMeal = new HashMap<>();
        boarMeal.put(Mice.class, 50);
        boarMeal.put(Caterpillar.class, 90);
        percentageMap.put(boar.getClass(), boarMeal);
        namesMap.put(boar.getClass(), "Boar");

        Buffalo buffalo = new Buffalo();
        HashMap<Class<?>,Integer> buffaloMeal = new HashMap<>();
        percentageMap.put(buffalo.getClass(), buffaloMeal);
        namesMap.put(buffalo.getClass(), "Buff");

        Caterpillar caterpillar = new Caterpillar();
        HashMap<Class<?>,Integer> caterpillarMeal = new HashMap<>();
        percentageMap.put(caterpillar.getClass(), caterpillarMeal);
        namesMap.put(caterpillar.getClass(), "Cate");

        Deer deer = new Deer();
        HashMap<Class<?>,Integer> deerMeal = new HashMap<>();
        percentageMap.put(deer.getClass(), deerMeal);
        namesMap.put(deer.getClass(), "Deer");

        Duck duck = new Duck();
        HashMap<Class<?>,Integer> duckMeal = new HashMap<>();
        duckMeal.put(Caterpillar.class, 90);
        percentageMap.put(duck.getClass(), duckMeal);
        namesMap.put(duck.getClass(), "Duck");

        Goat goat = new Goat();
        HashMap<Class<?>,Integer> goatMeal = new HashMap<>();
        percentageMap.put(goat.getClass(), goatMeal);
        namesMap.put(goat.getClass(), "Goat");

        Horse horse = new Horse();
        HashMap<Class<?>,Integer> horseMeal = new HashMap<>();
        percentageMap.put(horse.getClass(), horseMeal);
        namesMap.put(horse.getClass(), "Hors");

        Mice mice = new Mice();
        HashMap<Class<?>,Integer> miceMeal = new HashMap<>();
        miceMeal.put(Caterpillar.class, 90);
        percentageMap.put(mice.getClass(), miceMeal);
        namesMap.put(mice.getClass(), "Mice");

        Rabbit rabbit = new Rabbit();
        HashMap<Class<?>,Integer> rabbitMeal = new HashMap<>();
        percentageMap.put(rabbit.getClass(), rabbitMeal);
        namesMap.put(rabbit.getClass(), "Rabb");

        Sheep sheep = new Sheep();
        HashMap<Class<?>,Integer> sheepMeal = new HashMap<>();
        percentageMap.put(sheep.getClass(), sheepMeal);
        namesMap.put(sheep.getClass(), "Shee");

        Bear bear = new Bear();
        HashMap<Class<?>,Integer> bearMeal = new HashMap<>();
        bearMeal.put(Boa.class, 80);
        bearMeal.put(Horse.class, 40);
        bearMeal.put(Deer.class, 80);
        bearMeal.put(Rabbit.class, 40);
        bearMeal.put(Mice.class, 90);
        bearMeal.put(Goat.class, 70);
        bearMeal.put(Sheep.class, 70);
        bearMeal.put(Boar.class, 50);
        bearMeal.put(Buffalo.class, 20);
        bearMeal.put(Duck.class, 10);
        percentageMap.put(bear.getClass(), bearMeal);
        namesMap.put(bear.getClass(), "Bear");

        Boa boa = new Boa();
        HashMap<Class<?>,Integer> boaMeal = new HashMap<>();
        boaMeal.put(Fox.class, 15);
        boaMeal.put(Rabbit.class, 20);
        boaMeal.put(Mice.class, 40);
        boaMeal.put(Duck.class, 10);
        percentageMap.put(boa.getClass(), boaMeal);
        namesMap.put(boa.getClass(), "Boa ");

        Eagle eagle = new Eagle();
        HashMap<Class<?>,Integer> eagleMeal = new HashMap<>();
        eagleMeal.put(Fox.class, 10);
        eagleMeal.put(Rabbit.class, 90);
        eagleMeal.put(Mice.class, 90);
        eagleMeal.put(Duck.class, 80);
        percentageMap.put(eagle.getClass(), eagleMeal);
        namesMap.put(eagle.getClass(), "Eagl");

        Fox fox = new Fox();
        HashMap<Class<?>,Integer> foxMeal = new HashMap<>();
        foxMeal.put(Rabbit.class, 70);
        foxMeal.put(Mice.class, 90);
        foxMeal.put(Duck.class, 60);
        foxMeal.put(Caterpillar.class, 40);
        percentageMap.put(fox.getClass(), foxMeal);
        namesMap.put(fox.getClass(), "Fox ");

        Wolf wolf = new Wolf();
        HashMap<Class<?>,Integer> wolfMeal = new HashMap<>();
        wolfMeal.put(Horse.class, 10);
        wolfMeal.put(Deer.class, 15);
        wolfMeal.put(Rabbit.class, 60);
        wolfMeal.put(Mice.class, 80);
        wolfMeal.put(Goat.class, 60);
        wolfMeal.put(Sheep.class, 70);
        wolfMeal.put(Boar.class, 15);
        wolfMeal.put(Buffalo.class, 10);
        wolfMeal.put(Duck.class, 40);
        percentageMap.put(wolf.getClass(), wolfMeal);
        namesMap.put(wolf.getClass(), "Wolf");
    }

    public HashMap<Class<?>, HashMap<Class<?>, Integer>> getPercentageMap() {
        return percentageMap;
    }

    public HashMap<Class<?>, String> getNamesMap() {
        return namesMap;
    }
}
