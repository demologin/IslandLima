package com.javarush.island.karpiza.animals.herbivore;


import com.javarush.island.karpiza.Settings;
import com.javarush.island.karpiza.animals.Animal;
import com.javarush.island.karpiza.island.Location;
import com.javarush.island.karpiza.plant.Plant;
import com.javarush.island.karpiza.service.Diet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Herbivore extends Animal {

    public Herbivore(int xLocation, int yLocation, int movingSpeed) {
        super(xLocation, yLocation, movingSpeed);
    }



    public void eat(List<? extends Animal> victims, Location location) {
        location.getLock().lock();

        try {
            Diet annotation = this.getClass().getAnnotation(Diet.class);

            List<? extends Animal> herbivoresList = new CopyOnWriteArrayList<>(victims);
            for (Animal animal : herbivoresList) {
                if (this.eatOrNot(annotation.eatBoar())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatBuffalo())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatCaterpillar())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatDeer())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatDuck())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatGoat())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatHorse())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatMouse())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatRabbit())) animal.die(victims, location);
                if (this.eatOrNot(annotation.eatSheep())) animal.die(victims,location);
            }
        } finally { location.getLock().unlock();

        }
    }
    public void eatPlant(List<Plant> plants, Location location) {
        location.getLock().lock();

        try {
            if (plants.size() > 350) {
                if (this instanceof Boar) {
                    double hunger = ((Boar) this).getHunger();
                    while (hunger < Boar.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Boar) this).setHunger(hunger);
                }
                if (this instanceof Buffalo) {
                    double hunger = ((Buffalo) this).getHunger();
                    while (hunger < Buffalo.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Buffalo) this).setHunger(hunger);
                }
                if (this instanceof Caterpillar) {
                    double hunger = ((Caterpillar) this).getHunger();
                    while (hunger < Caterpillar.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Caterpillar) this).setHunger(hunger);
                }
                if (this instanceof Deer) {
                    double hunger = ((Deer) this).getHunger();
                    while (hunger < Deer.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Deer) this).setHunger(hunger);
                }
                if (this instanceof Duck) {
                    double hunger = ((Duck) this).getHunger();
                    while (hunger < Duck.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Duck) this).setHunger(hunger);
                }
                if (this instanceof Goat) {
                    double hunger = ((Goat) this).getHunger();
                    while (hunger < Goat.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Goat) this).setHunger(hunger);
                }
                if (this instanceof Horse) {
                    double hunger = ((Horse) this).getHunger();
                    while (hunger < Horse.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Horse) this).setHunger(hunger);
                }
                if (this instanceof Mouse) {
                    double hunger = ((Mouse) this).getHunger();
                    while (hunger < Mouse.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Mouse) this).setHunger(hunger);
                }
                if (this instanceof Rabbit) {
                    double hunger = ((Rabbit) this).getHunger();
                    while (hunger < Rabbit.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Rabbit) this).setHunger(hunger);
                }
                if (this instanceof Sheep) {
                    double hunger = ((Sheep) this).getHunger();
                    while (hunger < Sheep.canEat) {
                        hunger++;
                        plants.remove(0);
                    }
                    ((Sheep) this).setHunger(hunger);
                }
            }

        } finally { location.getLock().unlock();}
    }
    public void checkHealth(List<Herbivore> herbivores, Location location){
        location.getLock().lock();

        try {
            if (this instanceof Boar){
                if (((Boar) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Buffalo){
                if (((Buffalo) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Caterpillar){
                if (((Caterpillar) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Deer){
                if (((Deer) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Duck){
                if (((Duck) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Goat){
                if (((Goat) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Horse){
                if (((Horse) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Mouse){
                if (((Mouse) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Rabbit){
                if (((Rabbit) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
            if (this instanceof Sheep){
                if (((Sheep) this).getHunger() <= 0){this.die(herbivores, location);
                }
            }
        } finally { location.getLock().unlock();

        }
    }
    public void hunger(Location location){
        location.getLock().lock();
        try {
            if (this instanceof Boar) {
                ((Boar) this).setHunger(((Boar) this).getHunger() - Boar.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Buffalo) {
                ((Buffalo) this).setHunger(((Buffalo) this).getHunger() - Buffalo.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Caterpillar) {
                ((Caterpillar) this).setHunger(((Caterpillar) this).getHunger() - Caterpillar.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Deer) {
                ((Deer) this).setHunger(((Deer) this).getHunger() - Deer.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Duck) {
                ((Duck) this).setHunger(((Duck) this).getHunger() - Duck.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Goat) {
                ((Goat) this).setHunger(((Goat) this).getHunger() - Goat.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Horse) {
                ((Horse) this).setHunger(((Horse) this).getHunger() - Horse.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Mouse) {
                ((Mouse) this).setHunger(((Mouse) this).getHunger() - Mouse.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Rabbit) {
                ((Rabbit) this).setHunger(((Rabbit) this).getHunger() - Rabbit.canEat / Settings.herbivoreHungerFactor);
            }
            if (this instanceof Sheep) {
                ((Sheep) this).setHunger(((Sheep) this).getHunger() - Sheep.canEat / Settings.herbivoreHungerFactor);
            }
        } finally {location.getLock().unlock();}
    }

}