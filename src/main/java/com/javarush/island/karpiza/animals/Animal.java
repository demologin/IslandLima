package com.javarush.island.karpiza.animals;

import com.javarush.island.karpiza.Settings;
import com.javarush.island.karpiza.animals.herbivore.*;
import com.javarush.island.karpiza.animals.predator.*;
import com.javarush.island.karpiza.island.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {

    private final int xLocation;
    private final int yLocation;
    private final int xMax = Settings.islandLength;
    private final int yMax = Settings.islandWidth;
    private final int movingSpeed;

    public Animal(int xLocation, int yLocation, int movingSpeed) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movingSpeed = movingSpeed;
    }

    public void move(Location location, Location[][] locations) {
        location.getLock().lock();
        try {
            int[] newCoordinates = this.getNewCoordinates();
            if (isLocationFree(location)) {
                locations[newCoordinates[0]][newCoordinates[1]].addAnimal(this);
                if (this instanceof Predator) {
                    location.getPredators().remove(this);
                }
                if (this instanceof Herbivore) {
                    location.getHerbivores().remove(this);
                }
            }
            else move(location, locations);
        } finally {
            location.getLock().unlock();
        }
    }

    private boolean isLocationFree(Location location){
        int maxCount = 0;
        boolean returnValue;
        if (this instanceof Boar){maxCount = Settings.boarMaxCount;}
        if (this instanceof Buffalo){maxCount = Settings.buffaloMaxCount;}
        if (this instanceof Caterpillar){maxCount = Settings.caterpillarMaxCount;}
        if (this instanceof Deer){maxCount = Settings.deerMaxCount;}
        if (this instanceof Duck){maxCount = Settings.duckMaxCount;}
        if (this instanceof Goat){maxCount = Settings.goatMaxCount;}
        if (this instanceof Horse){maxCount = Settings.horseMaxCount;}
        if (this instanceof Mouse){maxCount = Settings.mouseMaxCount;}
        if (this instanceof Rabbit){maxCount = Settings.rabbitMaxCount;}
        if (this instanceof Sheep){maxCount = Settings.sheepMaxCount;}
        if (this instanceof Anaconda){maxCount = Settings.anacondaMaxCount;}
        if (this instanceof Bear){maxCount = Settings.bearMaxCount;}
        if (this instanceof Eagle){maxCount = Settings.eagleMaxCount;}
        if (this instanceof Fox){maxCount = Settings.foxMaxCount;}
        if (this instanceof Wolf){maxCount = Settings.wolfMaxCount;}

        int herbivoresCountOnLocation = (int) location.getHerbivores().stream().filter(this::equals).count();
        int predatorsCountOnLocation = (int) location.getPredators().stream().filter(this::equals).count();
        if (this instanceof Predator && predatorsCountOnLocation < maxCount) returnValue = true;
        else returnValue = this instanceof Herbivore && herbivoresCountOnLocation < maxCount;
        return returnValue;
    }

    private int[] getNewCoordinates () {
        int[] returnCoordinates = new int[2];
        int direction = ThreadLocalRandom.current().nextInt(4);
        int newXLocation;
        int newYLocation;

        if (direction == 0) {
            newXLocation = xLocation + movingSpeed;
            newYLocation = yLocation;
        }else if (direction == 1) {
            newXLocation = xLocation;
            newYLocation = yLocation + movingSpeed;
        }else if (direction == 2){
            newXLocation = xLocation - movingSpeed;
            newYLocation = yLocation;
        }else {
            newXLocation = xLocation;
            newYLocation = yLocation - movingSpeed;
        }
        int x = (newXLocation + xMax) % xMax;
        int y = (newYLocation + yMax) % yMax;
        returnCoordinates[0] = x;
        returnCoordinates[1] = y;

        return returnCoordinates;
    }

    public void die(List<? extends Animal> animals, Location location){
        location.getLock().lock();
        try {
            animals.remove(this);
        }finally {
            location.getLock().unlock();
        }
    }

    public boolean eatOrNot(int chance){
        int random = ThreadLocalRandom.current().nextInt(101);
        return random <= chance;
    }

    public int random(int count){
        return ThreadLocalRandom.current().nextInt(count);
    }
}
