package com.javarush.island.karpiza.service;

import com.javarush.island.karpiza.IslandStart;
import com.javarush.island.karpiza.animals.herbivore.*;
import com.javarush.island.karpiza.animals.predator.*;
import com.javarush.island.karpiza.island.Island;
import com.javarush.island.karpiza.island.Location;

//Подводим статистику

public class Statistics implements Runnable {

    Location[][] locations;
    Island island;

    public Statistics(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        locations = island.getLocations();

        int total = 0;

        for (Location[] location : locations) {
            for (Location value : location) {
                total = total + calculate(value);
            }
        }

        if (total > 0){
            System.out.println("Total animal count: " + total);
            System.out.println("***********************************************************************************");
            System.out.println();
        }
        else IslandStart.stop();
    }

    public int calculate(Location location){
        int boar = location.getHerbivores().stream().filter(animal -> animal instanceof Boar).toList().size();
        int buffalo = location.getHerbivores().stream().filter(animal -> animal instanceof Buffalo).toList().size();
        int caterpillar = location.getHerbivores().stream().filter(animal -> animal instanceof Caterpillar).toList().size();
        int deer = location.getHerbivores().stream().filter(animal -> animal instanceof Deer).toList().size();
        int duck = location.getHerbivores().stream().filter(animal -> animal instanceof Duck).toList().size();
        int goat = location.getHerbivores().stream().filter(animal -> animal instanceof Goat).toList().size();
        int horse = location.getHerbivores().stream().filter(animal -> animal instanceof Horse).toList().size();
        int mouse = location.getHerbivores().stream().filter(animal -> animal instanceof Mouse).toList().size();
        int rabbit = location.getHerbivores().stream().filter(animal -> animal instanceof Rabbit).toList().size();
        int sheep = location.getHerbivores().stream().filter(animal -> animal instanceof Sheep).toList().size();
        int anaconda = location.getPredators().stream().filter(animal -> animal instanceof Anaconda).toList().size();
        int bear = location.getPredators().stream().filter(animal -> animal instanceof Bear).toList().size();
        int eagle = location.getPredators().stream().filter(animal -> animal instanceof Eagle).toList().size();
        int fox = location.getPredators().stream().filter(animal -> animal instanceof Fox).toList().size();
        int wolf = location.getPredators().stream().filter(animal -> animal instanceof Wolf).toList().size();

        System.out.print("Total animal count on location : " + (boar + buffalo + caterpillar + deer + duck + goat + horse + mouse + rabbit + sheep + anaconda + bear + eagle + fox + wolf + " |"));
        System.out.print("| Boar=" + boar);
        System.out.print("| Buffalo=" + buffalo);
        System.out.print("| Caterpillar=" + caterpillar);
        System.out.print("| Deer" + deer);
        System.out.print("| Duck=" + duck);
        System.out.print("| Goat=" + goat);
        System.out.print("| Horse=" + horse);
        System.out.print("| Mouse=" + mouse);
        System.out.print("| Rabbit=" + rabbit);
        System.out.print("| Sheep=" + sheep);
        System.out.print("| Anaconda=" + anaconda);
        System.out.print("| Bear=" + bear);
        System.out.print("| Eagle=" + eagle);
        System.out.print("| Fox=" + fox);
        System.out.print("| Wolf=" + wolf);
        System.out.println();

        return (boar + buffalo + caterpillar + deer + duck + goat + horse + mouse + rabbit + sheep + anaconda + bear + eagle + fox + wolf);
    }
}
