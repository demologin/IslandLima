package com.javarush.island.chebotareva.data;

import com.javarush.island.chebotareva.herbivorous.*;
import com.javarush.island.chebotareva.predator.*;

import java.util.HashMap;
import java.util.Map;

public class Animals {
    //создаем (массив) перечень всех возможных животных и растений
    Map<String, Map<String, Integer>> mapOfProbabilitiesToEat = new HashMap<>();
    Map<String, Integer> probabilitiesForWolf = new HashMap<>();
    Map<String, Integer> probabilitiesForSnake = new HashMap<>();
    Map<String, Integer> probabilitiesForFox = new HashMap<>();
    Map<String, Integer> probabilitiesForBear = new HashMap<>();
    Map<String, Integer> probabilitiesForEagle = new HashMap<>();
    Map<String, Integer> probabilitiesForHorse = new HashMap<>();
    Map<String, Integer> probabilitiesForDeer = new HashMap<>();
    Map<String, Integer> probabilitiesForRabbit = new HashMap<>();
    Map<String, Integer> probabilitiesForMouse = new HashMap<>();
    Map<String, Integer> probabilitiesForGoat = new HashMap<>();
    Map<String, Integer> probabilitiesForSheep = new HashMap<>();
    Map<String, Integer> probabilitiesForBoar = new HashMap<>();
    Map<String, Integer> probabilitiesForBuffalo = new HashMap<>();
    Map<String, Integer> probabilitiesForDuck = new HashMap<>();
    Map<String, Integer> probabilitiesForCaterpillar = new HashMap<>();
    Map<String, Integer> probabilitiesForPlant = new HashMap<>();

    public Map<String, Map<String, Integer>> fillMapOfProbabilities() {

        probabilitiesForWolf.put("Wolf", 0);
        probabilitiesForWolf.put("Snake", 0);
        probabilitiesForWolf.put("Fox", 0);
        probabilitiesForWolf.put("Bear", 0);
        probabilitiesForWolf.put("Eagle", 0);
        probabilitiesForWolf.put("Horse", 10);
        probabilitiesForWolf.put("Deer", 15);
        probabilitiesForWolf.put("Rabbit", 60);
        probabilitiesForWolf.put("Mouse", 80);
        probabilitiesForWolf.put("Goat", 60);
        probabilitiesForWolf.put("Sheep", 70);
        probabilitiesForWolf.put("Boar", 15);
        probabilitiesForWolf.put("Buffalo", 10);
        probabilitiesForWolf.put("Duck", 40);
        probabilitiesForWolf.put("Caterpillar", 0);
        probabilitiesForWolf.put("Plant", 0);
        mapOfProbabilitiesToEat.put("Wolf", probabilitiesForWolf);

        probabilitiesForSnake.put("Wolf", 0);
        probabilitiesForSnake.put("Snake", 0);
        probabilitiesForSnake.put("Fox", 15);
        probabilitiesForSnake.put("Bear", 0);
        probabilitiesForSnake.put("Eagle", 0);
        probabilitiesForSnake.put("Horse", 0);
        probabilitiesForSnake.put("Deer", 0);
        probabilitiesForSnake.put("Rabbit", 20);
        probabilitiesForSnake.put("Mouse", 40);
        probabilitiesForSnake.put("Goat", 0);
        probabilitiesForSnake.put("Sheep", 0);
        probabilitiesForSnake.put("Boar", 0);
        probabilitiesForSnake.put("Buffalo", 0);
        probabilitiesForSnake.put("Duck", 10);
        probabilitiesForSnake.put("Caterpillar", 0);
        probabilitiesForSnake.put("Plant", 0);
        mapOfProbabilitiesToEat.put("Snake", probabilitiesForSnake);

        probabilitiesForFox.put("Wolf", 0);
        probabilitiesForFox.put("Snake", 0);
        probabilitiesForFox.put("Fox", 0);
        probabilitiesForFox.put("Bear", 0);
        probabilitiesForFox.put("Eagle", 0);
        probabilitiesForFox.put("Horse", 0);
        probabilitiesForFox.put("Deer", 0);
        probabilitiesForFox.put("Rabbit", 70);
        probabilitiesForFox.put("Mouse", 90);
        probabilitiesForFox.put("Goat", 0);
        probabilitiesForFox.put("Sheep", 0);
        probabilitiesForFox.put("Boar", 0);
        probabilitiesForFox.put("Buffalo", 0);
        probabilitiesForFox.put("Duck", 60);
        probabilitiesForFox.put("Caterpillar", 40);
        probabilitiesForFox.put("Plant", 0);
        mapOfProbabilitiesToEat.put("Fox", probabilitiesForFox);

        probabilitiesForBear.put("Wolf", 0);
        probabilitiesForBear.put("Snake", 80);
        probabilitiesForBear.put("Fox", 60);
        probabilitiesForBear.put("Bear", 0);
        probabilitiesForBear.put("Eagle", 0);
        probabilitiesForBear.put("Horse", 40);
        probabilitiesForBear.put("Deer", 80);
        probabilitiesForBear.put("Rabbit", 80);
        probabilitiesForBear.put("Mouse", 90);
        probabilitiesForBear.put("Goat", 70);
        probabilitiesForBear.put("Sheep", 70);
        probabilitiesForBear.put("Boar", 50);
        probabilitiesForBear.put("Buffalo", 20);
        probabilitiesForBear.put("Duck", 10);
        probabilitiesForBear.put("Caterpillar", 0);
        probabilitiesForBear.put("Plant", 0);
        mapOfProbabilitiesToEat.put("Bear", probabilitiesForBear);

        probabilitiesForEagle.put("Wolf", 0);
        probabilitiesForEagle.put("Snake", 0);
        probabilitiesForEagle.put("Fox", 10);
        probabilitiesForEagle.put("Bear", 0);
        probabilitiesForEagle.put("Eagle", 0);
        probabilitiesForEagle.put("Horse", 0);
        probabilitiesForEagle.put("Deer", 0);
        probabilitiesForEagle.put("Rabbit", 90);
        probabilitiesForEagle.put("Mouse", 90);
        probabilitiesForEagle.put("Goat", 0);
        probabilitiesForEagle.put("Sheep", 0);
        probabilitiesForEagle.put("Boar", 0);
        probabilitiesForEagle.put("Buffalo", 0);
        probabilitiesForEagle.put("Duck", 80);
        probabilitiesForEagle.put("Caterpillar", 0);
        probabilitiesForEagle.put("Plant", 0);
        mapOfProbabilitiesToEat.put("Eagle", probabilitiesForEagle);

        probabilitiesForHorse.put("Wolf", 0);
        probabilitiesForHorse.put("Snake", 0);
        probabilitiesForHorse.put("Fox", 0);
        probabilitiesForHorse.put("Bear", 0);
        probabilitiesForHorse.put("Eagle", 0);
        probabilitiesForHorse.put("Horse", 0);
        probabilitiesForHorse.put("Deer", 0);
        probabilitiesForHorse.put("Rabbit", 0);
        probabilitiesForHorse.put("Mouse", 0);
        probabilitiesForHorse.put("Goat", 0);
        probabilitiesForHorse.put("Sheep", 0);
        probabilitiesForHorse.put("Boar", 0);
        probabilitiesForHorse.put("Buffalo", 0);
        probabilitiesForHorse.put("Duck", 0);
        probabilitiesForHorse.put("Caterpillar", 0);
        probabilitiesForHorse.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Horse", probabilitiesForHorse);

        probabilitiesForDeer.put("Wolf", 0);
        probabilitiesForDeer.put("Snake", 0);
        probabilitiesForDeer.put("Fox", 0);
        probabilitiesForDeer.put("Bear", 0);
        probabilitiesForDeer.put("Eagle", 0);
        probabilitiesForDeer.put("Horse", 0);
        probabilitiesForDeer.put("Deer", 0);
        probabilitiesForDeer.put("Rabbit", 0);
        probabilitiesForDeer.put("Mouse", 0);
        probabilitiesForDeer.put("Goat", 0);
        probabilitiesForDeer.put("Sheep", 0);
        probabilitiesForDeer.put("Boar", 0);
        probabilitiesForDeer.put("Buffalo", 0);
        probabilitiesForDeer.put("Duck", 0);
        probabilitiesForDeer.put("Caterpillar", 0);
        probabilitiesForDeer.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Deer", probabilitiesForDeer);

        probabilitiesForRabbit.put("Wolf", 0);
        probabilitiesForRabbit.put("Snake", 0);
        probabilitiesForRabbit.put("Fox", 0);
        probabilitiesForRabbit.put("Bear", 0);
        probabilitiesForRabbit.put("Eagle", 0);
        probabilitiesForRabbit.put("Horse", 0);
        probabilitiesForRabbit.put("Deer", 0);
        probabilitiesForRabbit.put("Rabbit", 0);
        probabilitiesForRabbit.put("Mouse", 0);
        probabilitiesForRabbit.put("Goat", 0);
        probabilitiesForRabbit.put("Sheep", 0);
        probabilitiesForRabbit.put("Boar", 0);
        probabilitiesForRabbit.put("Buffalo", 0);
        probabilitiesForRabbit.put("Duck", 0);
        probabilitiesForRabbit.put("Caterpillar", 0);
        probabilitiesForRabbit.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Rabbit", probabilitiesForRabbit);

        probabilitiesForMouse.put("Wolf", 0);
        probabilitiesForMouse.put("Snake", 0);
        probabilitiesForMouse.put("Fox", 0);
        probabilitiesForMouse.put("Bear", 0);
        probabilitiesForMouse.put("Eagle", 0);
        probabilitiesForMouse.put("Horse", 0);
        probabilitiesForMouse.put("Deer", 0);
        probabilitiesForMouse.put("Rabbit", 0);
        probabilitiesForMouse.put("Mouse", 0);
        probabilitiesForMouse.put("Goat", 0);
        probabilitiesForMouse.put("Sheep", 0);
        probabilitiesForMouse.put("Boar", 0);
        probabilitiesForMouse.put("Buffalo", 0);
        probabilitiesForMouse.put("Duck", 0);
        probabilitiesForMouse.put("Caterpillar", 90);
        probabilitiesForMouse.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Mouse", probabilitiesForMouse);

        probabilitiesForGoat.put("Wolf", 0);
        probabilitiesForGoat.put("Snake", 0);
        probabilitiesForGoat.put("Fox", 0);
        probabilitiesForGoat.put("Bear", 0);
        probabilitiesForGoat.put("Eagle", 0);
        probabilitiesForGoat.put("Horse", 0);
        probabilitiesForGoat.put("Deer", 0);
        probabilitiesForGoat.put("Rabbit", 0);
        probabilitiesForGoat.put("Mouse", 0);
        probabilitiesForGoat.put("Goat", 0);
        probabilitiesForGoat.put("Sheep", 0);
        probabilitiesForGoat.put("Boar", 0);
        probabilitiesForGoat.put("Buffalo", 0);
        probabilitiesForGoat.put("Duck", 0);
        probabilitiesForGoat.put("Caterpillar", 0);
        probabilitiesForGoat.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Goat", probabilitiesForGoat);

        probabilitiesForSheep.put("Wolf", 0);
        probabilitiesForSheep.put("Snake", 0);
        probabilitiesForSheep.put("Fox", 0);
        probabilitiesForSheep.put("Bear", 0);
        probabilitiesForSheep.put("Eagle", 0);
        probabilitiesForSheep.put("Horse", 0);
        probabilitiesForSheep.put("Deer", 0);
        probabilitiesForSheep.put("Rabbit", 0);
        probabilitiesForSheep.put("Mouse", 0);
        probabilitiesForSheep.put("Goat", 0);
        probabilitiesForSheep.put("Sheep", 0);
        probabilitiesForSheep.put("Boar", 0);
        probabilitiesForSheep.put("Buffalo", 0);
        probabilitiesForSheep.put("Duck", 0);
        probabilitiesForSheep.put("Caterpillar", 0);
        probabilitiesForSheep.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Sheep", probabilitiesForSheep);

        probabilitiesForBoar.put("Wolf", 0);
        probabilitiesForBoar.put("Snake", 0);
        probabilitiesForBoar.put("Fox", 0);
        probabilitiesForBoar.put("Bear", 0);
        probabilitiesForBoar.put("Eagle", 0);
        probabilitiesForBoar.put("Horse", 0);
        probabilitiesForBoar.put("Deer", 0);
        probabilitiesForBoar.put("Rabbit", 0);
        probabilitiesForBoar.put("Mouse", 50);
        probabilitiesForBoar.put("Goat", 0);
        probabilitiesForBoar.put("Sheep", 0);
        probabilitiesForBoar.put("Boar", 0);
        probabilitiesForBoar.put("Buffalo", 0);
        probabilitiesForBoar.put("Duck", 0);
        probabilitiesForBoar.put("Caterpillar", 90);
        probabilitiesForBoar.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Boar", probabilitiesForBoar);

        probabilitiesForBuffalo.put("Wolf", 0);
        probabilitiesForBuffalo.put("Snake", 0);
        probabilitiesForBuffalo.put("Fox", 0);
        probabilitiesForBuffalo.put("Bear", 0);
        probabilitiesForBuffalo.put("Eagle", 0);
        probabilitiesForBuffalo.put("Horse", 0);
        probabilitiesForBuffalo.put("Deer", 0);
        probabilitiesForBuffalo.put("Rabbit", 0);
        probabilitiesForBuffalo.put("Mouse", 0);
        probabilitiesForBuffalo.put("Goat", 0);
        probabilitiesForBuffalo.put("Sheep", 0);
        probabilitiesForBuffalo.put("Boar", 0);
        probabilitiesForBuffalo.put("Buffalo", 0);
        probabilitiesForBuffalo.put("Duck", 0);
        probabilitiesForBuffalo.put("Caterpillar", 0);
        probabilitiesForBuffalo.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Buffalo", probabilitiesForBuffalo);

        probabilitiesForDuck.put("Wolf", 0);
        probabilitiesForDuck.put("Snake", 0);
        probabilitiesForDuck.put("Fox", 0);
        probabilitiesForDuck.put("Bear", 0);
        probabilitiesForDuck.put("Eagle", 0);
        probabilitiesForDuck.put("Horse", 0);
        probabilitiesForDuck.put("Deer", 0);
        probabilitiesForDuck.put("Rabbit", 0);
        probabilitiesForDuck.put("Mouse", 0);
        probabilitiesForDuck.put("Goat", 0);
        probabilitiesForDuck.put("Sheep", 0);
        probabilitiesForDuck.put("Boar", 0);
        probabilitiesForDuck.put("Buffalo", 0);
        probabilitiesForDuck.put("Duck", 0);
        probabilitiesForDuck.put("Caterpillar", 90);
        probabilitiesForDuck.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Duck", probabilitiesForDuck);

        probabilitiesForCaterpillar.put("Wolf", 0);
        probabilitiesForCaterpillar.put("Snake", 0);
        probabilitiesForCaterpillar.put("Fox", 0);
        probabilitiesForCaterpillar.put("Bear", 0);
        probabilitiesForCaterpillar.put("Eagle", 0);
        probabilitiesForCaterpillar.put("Horse", 0);
        probabilitiesForCaterpillar.put("Deer", 0);
        probabilitiesForCaterpillar.put("Rabbit", 0);
        probabilitiesForCaterpillar.put("Mouse", 0);
        probabilitiesForCaterpillar.put("Goat", 0);
        probabilitiesForCaterpillar.put("Sheep", 0);
        probabilitiesForCaterpillar.put("Boar", 0);
        probabilitiesForCaterpillar.put("Buffalo", 0);
        probabilitiesForCaterpillar.put("Duck", 0);
        probabilitiesForCaterpillar.put("Caterpillar", 0);
        probabilitiesForCaterpillar.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Caterpillar", probabilitiesForCaterpillar);

        probabilitiesForPlant.put("Wolf", 0);
        probabilitiesForPlant.put("Snake", 0);
        probabilitiesForPlant.put("Fox", 0);
        probabilitiesForPlant.put("Bear", 0);
        probabilitiesForPlant.put("Eagle", 0);
        probabilitiesForPlant.put("Horse", 0);
        probabilitiesForPlant.put("Deer", 0);
        probabilitiesForPlant.put("Rabbit", 0);
        probabilitiesForPlant.put("Mouse", 0);
        probabilitiesForPlant.put("Goat", 0);
        probabilitiesForPlant.put("Sheep", 0);
        probabilitiesForPlant.put("Boar", 0);
        probabilitiesForPlant.put("Buffalo", 0);
        probabilitiesForPlant.put("Duck", 0);
        probabilitiesForPlant.put("Caterpillar", 0);
        probabilitiesForPlant.put("Plant", 100);
        mapOfProbabilitiesToEat.put("Plant", probabilitiesForPlant);

        return mapOfProbabilitiesToEat;
    }

    public String[] getAnimal() {
        String[] animals = new String[]{"Wolf", "Snake", "Fox", "Bear", "Eagle", "Horse", "Deer", "Rabbit", "Mouse", "Goat",
                "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Plant"};
        return animals;
    }

    // создаем мапу ключ (наименование животного) - значение (объект класса животного)
    public Map<String, Animal> makeAnimalMainSet() {
        Map<String, Animal> animalMainSet = new HashMap<>();
        animalMainSet.put("Wolf", new Wolf());
        animalMainSet.put("Snake", new Snake());
        animalMainSet.put("Fox", new Fox());
        animalMainSet.put("Bear", new Bear());
        animalMainSet.put("Eagle", new Eagle());
        animalMainSet.put("Horse", new Horse());
        animalMainSet.put("Deer", new Deer());
        animalMainSet.put("Rabbit", new Rabbit());
        animalMainSet.put("Mouse", new Mouse());
        animalMainSet.put("Goat", new Goat());
        animalMainSet.put("Sheep", new Sheep());
        animalMainSet.put("Boar", new Boar());
        animalMainSet.put("Buffalo", new Buffalo());
        animalMainSet.put("Duck", new Duck());
        animalMainSet.put("Caterpillar", new Caterpillar());
        animalMainSet.put("Plant", new Plant());

        return animalMainSet;
    }
}
