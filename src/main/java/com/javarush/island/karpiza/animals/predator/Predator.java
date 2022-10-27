package com.javarush.island.karpiza.animals.predator;

import com.javarush.island.karpiza.Settings;
import com.javarush.island.karpiza.animals.Animal;
import com.javarush.island.karpiza.animals.herbivore.*;
import com.javarush.island.karpiza.island.Location;
import com.javarush.island.karpiza.service.Diet;

import java.util.List;

public abstract class Predator extends Animal {


    public Predator(int xLocation, int yLocation, int movingSpeed) {
        super(xLocation, yLocation, movingSpeed);
    }


    public void eat (List<Herbivore> herbivores, Location location){
        location.getLock().lock();
        try {
            Predator predator = this;

            if (predator instanceof Anaconda){
                double appetite = ((Anaconda) predator).getHunger();
                if (appetite < Anaconda.canEat){
                    int hunt = predator.hunt(herbivores, location);
                    if (hunt > Anaconda.canEat) {((Anaconda) predator).setHunger((int) Anaconda.canEat);}
                    else ((Anaconda) predator).setHunger(((Anaconda) predator).getHunger() + hunt);
                }
            }
            if (predator instanceof Bear){
                double appetite = ((Bear) predator).getHunger();
                if (appetite < Bear.canEat){
                    int hunt = predator.hunt(herbivores, location);
                    if (hunt > Bear.canEat) {((Bear) predator).setHunger((int) Bear.canEat);}
                    else ((Bear) predator).setHunger(((Bear) predator).getHunger() + hunt);
                }
            }
            if (predator instanceof Eagle){
                double appetite = ((Eagle) predator).getHunger();
                if (appetite < Eagle.canEat){
                    int hunt = predator.hunt(herbivores, location);
                    if (hunt > Eagle.canEat) {((Eagle) predator).setHunger((int) Eagle.canEat);}
                    else ((Eagle) predator).setHunger(((Eagle) predator).getHunger() + hunt);
                }
            }
            if (predator instanceof Fox){
                double appetite = ((Fox) predator).getHunger();
                if (appetite < Fox.canEat){
                    int hunt = predator.hunt(herbivores, location);
                    if (hunt > Fox.canEat) {((Fox) predator).setHunger((int) Fox.canEat);}
                    else ((Fox) predator).setHunger(((Fox) predator).getHunger() + hunt);
                }
            }
            if (predator instanceof Wolf){
                double appetite = ((Wolf) predator).getHunger();
                if (appetite < Wolf.canEat){
                    int hunt = predator.hunt(herbivores, location);
                    if (hunt > Wolf.canEat) {((Wolf) predator).setHunger((int) Wolf.canEat);}
                    else ((Wolf) predator).setHunger(((Wolf) predator).getHunger() + hunt);
                }
            }
        } finally { location.getLock().unlock();

        }
    }

    public int hunt(List<Herbivore> herbivores, Location location){
        int hungerCount = 0;
        Predator predator = this;
        location.getLock().lock();

        try {
            Diet annotation = predator.getClass().getAnnotation(Diet.class);

            if (herbivores.size() > 0) {
                Herbivore herbivore = herbivores.get(random(herbivores.size()));

                if (predator.eatOrNot(annotation.eatBoar()) && herbivore instanceof Boar) {herbivore.die(herbivores, location);
                    hungerCount = (int) Bear.weight;
                }
                if (predator.eatOrNot(annotation.eatBuffalo()) && herbivore instanceof Buffalo) {herbivore.die(herbivores, location);
                    hungerCount = (int) Buffalo.weight;
                }
                if (predator.eatOrNot(annotation.eatCaterpillar()) && herbivore instanceof Caterpillar) {herbivore.die(herbivores, location);
                    hungerCount = (int) Caterpillar.weight;
                }
                if (predator.eatOrNot(annotation.eatDeer()) && herbivore instanceof Deer) {herbivore.die(herbivores, location);
                    hungerCount = (int) Deer.weight;
                }
                if (predator.eatOrNot(annotation.eatDuck()) && herbivore instanceof Duck) {herbivore.die(herbivores, location);
                    hungerCount = (int) Duck.weight;
                }
                if (predator.eatOrNot(annotation.eatGoat()) && herbivore instanceof Goat) {herbivore.die(herbivores, location);
                    hungerCount = (int) Goat.weight;
                }
                if (predator.eatOrNot(annotation.eatHorse()) && herbivore instanceof Horse) {herbivore.die(herbivores, location);
                    hungerCount = (int) Horse.weight;
                }
                if (predator.eatOrNot(annotation.eatMouse()) && herbivore instanceof Mouse) {herbivore.die(herbivores, location);
                    hungerCount = 1;
                }
                if (predator.eatOrNot(annotation.eatRabbit()) && herbivore instanceof Rabbit) {herbivore.die(herbivores, location);
                    hungerCount = (int) Rabbit.weight;
                }
                if (predator.eatOrNot(annotation.eatSheep()) && herbivore instanceof Sheep) {herbivore.die(herbivores, location);
                    hungerCount = (int) Sheep.weight;
                }
            }
        } finally {location.getLock().unlock();

        }
        return hungerCount;
    }

    public void checkHealth(List<Predator> predators, Location location){
        location.getLock().lock();
        try {
            if (this instanceof Anaconda){
                if (((Anaconda) this).getHunger() <= 0.5){this.die(predators, location);}
            }
            if (this instanceof Eagle){
                if (((Eagle) this).getHunger() <= 0.5){this.die(predators, location);}
            }
            if (this instanceof Bear){
                if (((Bear) this).getHunger() <= 0.5){this.die(predators, location);}
            }
            if (this instanceof Fox){
                if (((Fox) this).getHunger() <= 0.5){this.die(predators, location);}
            }
            if (this instanceof Wolf){
                if (((Wolf) this).getHunger() <= 0.5){this.die(predators, location);}
            }
        } finally { location.getLock().unlock();
        }
    }

    public void hunger(Location location){
        location.getLock().lock();
        try {
            if (this instanceof Anaconda){
                ((Anaconda) this).setHunger(((Anaconda) this).getHunger() - Anaconda.canEat/ Settings.predatorHungerFactor);
            }
            if (this instanceof Bear){
                ((Bear) this).setHunger(((Bear) this).getHunger() - Bear.canEat/Settings.predatorHungerFactor);
            }
            if (this instanceof Eagle){
                ((Eagle) this).setHunger(((Eagle) this).getHunger() - Eagle.canEat/Settings.predatorHungerFactor);
            }
            if (this instanceof Fox){
                ((Fox) this).setHunger(((Fox) this).getHunger() - Fox.canEat/Settings.predatorHungerFactor);
            }
            if (this instanceof Wolf){
                ((Wolf) this).setHunger(((Wolf) this).getHunger() - Wolf.canEat/Settings.predatorHungerFactor);
            }
        } finally {
            location.getLock().unlock();
        }
    }
}