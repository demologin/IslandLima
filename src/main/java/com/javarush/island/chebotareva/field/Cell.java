package com.javarush.island.chebotareva.field;

import com.javarush.island.chebotareva.data.Animal;
import com.javarush.island.chebotareva.data.Animals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {
    public Cell() {
        createMapWithEmptyList();
    }

    public Map<String, LinkedList<Animal>> animalSet = new HashMap<>();
    Animals animals = new Animals();
    String[] allanimals = animals.getAnimal();

    public Map<String, LinkedList<Animal>> createMapWithEmptyList() {
        for (int i = 0; i < allanimals.length; i++) {
            animalSet.put(allanimals[i], new LinkedList<Animal>());
        }
        return animalSet;
    }

    public void firstAnimalCreate() {
        int num = 0;
        int createOrNot;
        int animalAmountToCreate;

        for (int i = 0; i < animals.getAnimal().length; i++) {
            createOrNot = ThreadLocalRandom.current().nextInt(0, 2);
            if (createOrNot > 0) {
                String name = allanimals[i];
                Animal animal = animals.makeAnimalMainSet().get(name);
                num = animal.getMaxAnimalAmount() + 1;//ниже нужно передать макс кол-во животных данного вида
                animalAmountToCreate = ThreadLocalRandom.current().nextInt(0, num);
                for (int j = 0; j < animalAmountToCreate; j++) {
                    animalSet.get(name).add(animal);
                }
            }
        }
    }

    public String countOfAnimal() {
        String whoHowMany = "";
        for (Map.Entry<String, LinkedList<Animal>> entry : animalSet.entrySet()) {
            String key = entry.getKey();
            String newLine = key.substring(0, 1);
            whoHowMany = whoHowMany + " " + newLine + ":" + entry.getValue().size();
        }
        return whoHowMany;
    }

    public void eatInCell() {

        String whoTryToEat = null;
        String whoMightBeEated = null;
        String[] animalsName = animals.getAnimal();
        for (int k = 0; k < animalsName.length; k++) {
            for (int l = 0; l < animalsName.length; l++) {
                whoTryToEat = animalsName[k];
                whoMightBeEated = animalsName[l];
                if (animals.fillMapOfProbabilities().get(whoTryToEat).get(whoMightBeEated) > 0) {
                    int eatOrNot = ThreadLocalRandom.current().nextInt(100);
                    if (eatOrNot < animals.fillMapOfProbabilities().get(whoTryToEat).get(whoMightBeEated)) {
                        if (!animalSet.get(whoMightBeEated).isEmpty() && !animalSet.get(whoTryToEat).isEmpty()) {
                            animalSet.get(whoMightBeEated).remove(0);
                        }
                    }
                }
            }
        }
    }
}
