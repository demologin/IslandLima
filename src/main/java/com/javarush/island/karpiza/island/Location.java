package com.javarush.island.karpiza.island;

import com.javarush.island.karpiza.Settings;
import com.javarush.island.karpiza.animals.Animal;
import com.javarush.island.karpiza.animals.herbivore.*;
import com.javarush.island.karpiza.animals.predator.*;
import com.javarush.island.karpiza.plant.Plant;
import com.javarush.island.karpiza.service.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Location {

    private final int x;
    private final int y;

    private final Lock lock = new ReentrantLock(true);

    public Lock getLock() {
        return lock;
    }

    public Location(Coordinate coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    private final List<Herbivore> herbivores = new ArrayList<>();
    private final List<Predator> predators = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public List<Herbivore> getHerbivores() {
        return herbivores;
    }

    public List<Predator> getPredators() {
        return predators;
    }

    public List<Plant> getPlants() {
        return plants;
    }


    public void addHerbivoresToList(){
        for (int i = 0; i < random(Settings.boarMaxCount); i++) {
            herbivores.add(new Boar(x, y));
        }
        for (int i = 0; i < random(Settings.buffaloMaxCount); i++) {
            herbivores.add(new Buffalo(x, y));
        }
        for (int i = 0; i < random(Settings.caterpillarMaxCount); i++) {
            herbivores.add(new Caterpillar(x, y));
        }
        for (int i = 0; i < random(Settings.deerMaxCount); i++) {
            herbivores.add(new Deer(x, y));
        }
        for (int i = 0; i < random(Settings.duckMaxCount); i++) {
            herbivores.add(new Duck(x, y));
        }
        for (int i = 0; i < random(Settings.goatMaxCount); i++) {
            herbivores.add(new Goat(x, y));
        }
        for (int i = 0; i < random(Settings.horseMaxCount); i++) {
            herbivores.add(new Horse(x, y));
        }
        for (int i = 0; i < random(Settings.mouseMaxCount); i++) {
            herbivores.add(new Mouse(x, y));
        }
        for (int i = 0; i < random(Settings.rabbitMaxCount); i++) {
            herbivores.add(new Rabbit(x, y));
        }
        for (int i = 0; i < random(Settings.sheepMaxCount); i++) {
            herbivores.add(new Sheep(x, y));
        }
    }

    public void addPredatorsToList(){
        for (int i = 0; i < random(Settings.anacondaMaxCount); i++) {
            predators.add(new Anaconda(x, y));
        }
        for (int i = 0; i < random(Settings.bearMaxCount); i++) {
            predators.add(new Bear(x, y));
        }
        for (int i = 0; i < random(Settings.eagleMaxCount); i++) {
            predators.add(new Eagle(x, y));
        }
        for (int i = 0; i < random(Settings.foxMaxCount); i++) {
            predators.add(new Fox(x, y));
        }
        for (int i = 0; i < random(Settings.wolfMaxCount); i++) {
            predators.add(new Wolf(x, y));
        }
    }

    public void addAnimal(Animal animal){
        this.getLock().lock();
        try {
            if (animal instanceof Predator){
                predators.add((Predator) animal);
            }
            if (animal instanceof Herbivore){
                herbivores.add((Herbivore) animal);
            }
        }finally {
            this.getLock().unlock();
        }

    }

    public void addPlantsToList(){
        for (int i = 0; i < 10000; i++) {
            plants.add(new Plant(1));
        }
    }


    public void eating(){
        this.getLock().lock();
        try {

            List<Predator> predatorsList = new CopyOnWriteArrayList<>(predators);
            List<Herbivore> herbivoresList = new CopyOnWriteArrayList<>(herbivores);

            for (Predator predator : predatorsList) {
                predator.eat(herbivores, this);
                predator.checkHealth(predators, this);
            }
            for (Herbivore herbivore : herbivoresList) {
                herbivore.eat(herbivores, this);
                herbivore.eatPlant(plants, this);
                herbivore.checkHealth(herbivores, this);
            }

        } finally {
            this.getLock().unlock();
        }
    }

    public void startReproduct(){
        this.getLock().lock();
        try {
            for (int i = 0; i < randomReproductCount(Settings.boarMaxCount/Settings.boarBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Boar).toList().size() > 2)
                    herbivores.add(new Boar(x, y));
            }
            for (int i = 0; i < randomReproductCount(Settings.buffaloMaxCount/Settings.buffaloBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Buffalo).toList().size() > 2){
                    herbivores.add(new Buffalo(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.caterpillarMaxCount/Settings.caterpillarBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Caterpillar).toList().size() > 2){
                    herbivores.add(new Caterpillar(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.deerMaxCount/Settings.deerBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Deer).toList().size() > 2){
                    herbivores.add(new Deer(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.duckMaxCount/Settings.duckBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Duck).toList().size() > 2){
                    herbivores.add(new Duck(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.goatMaxCount/Settings.goatBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Goat).toList().size() > 2){
                    herbivores.add(new Goat(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.horseMaxCount/Settings.horseBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Horse).toList().size() > 2){
                    herbivores.add(new Horse(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.mouseMaxCount/Settings.mouseBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Mouse).toList().size() > 2){
                    herbivores.add(new Mouse(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.rabbitMaxCount/Settings.rabbitBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Rabbit).toList().size() > 2){
                    herbivores.add(new Rabbit(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.sheepMaxCount/Settings.sheepBirthRate); i++) {
                if (herbivores.stream().filter(animal -> animal instanceof Sheep).toList().size() > 2){
                    herbivores.add(new Sheep(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.anacondaMaxCount/Settings.anacondaBirthRate); i++) {
                if (predators.stream().filter(animal -> animal instanceof Anaconda).toList().size() > 2){
                    predators.add(new Anaconda(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.bearMaxCount/Settings.bearBirthRate); i++) {
                if (predators.stream().filter(animal -> animal instanceof Bear).toList().size() > 2){
                    predators.add(new Bear(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.eagleMaxCount/Settings.eagleBirthRate); i++) {
                if (predators.stream().filter(animal -> animal instanceof Eagle).toList().size() > 2){
                    predators.add(new Eagle(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.foxMaxCount/Settings.foxBirthRate); i++) {
                if (predators.stream().filter(animal -> animal instanceof Fox).toList().size() > 2){
                    predators.add(new Fox(x, y));}
            }
            for (int i = 0; i < randomReproductCount(Settings.wolfMaxCount/Settings.wolfBirthRate); i++) {
                if (predators.stream().filter(animal -> animal instanceof Wolf).toList().size() > 2){
                    predators.add(new Wolf(x, y));}
            }
        }
        finally {
            this.getLock().unlock();
        }
    }

    private int random(int animalCount){
        return ThreadLocalRandom.current().nextInt(animalCount);
    }

    private int randomReproductCount(int animalCount){
        int returnNumber;
        if (animalCount >= 2) {
            returnNumber = ThreadLocalRandom.current().nextInt(animalCount/2);
        }
        else returnNumber = 0;
        return returnNumber;
    }

    public void moveAnimals(Island island){
        island.getLock().lock();
        try {
            List<Predator> predatorsList = new CopyOnWriteArrayList<>(predators);
            List<Herbivore> herbivoresList = new CopyOnWriteArrayList<>(herbivores);

            for (Predator predator : predatorsList) {
                predator.move(this, Island.locations);
                predator.hunger(this);
                predator.checkHealth(predators, this);
            }
            for (Herbivore herbivore : herbivoresList) {
                herbivore.move(this, Island.locations);
                herbivore.hunger(this);
                herbivore.checkHealth(herbivores, this);
            }
        }
        finally {
            island.getLock().unlock();
        }
    }
}