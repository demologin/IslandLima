package com.javarush.island.ivanilov.creatures;

import com.javarush.island.ivanilov.behaviours.Eating;
import com.javarush.island.ivanilov.behaviours.EatingCarrion;
import com.javarush.island.ivanilov.behaviours.Movable;
import com.javarush.island.ivanilov.entities.AnimalLimits;
import com.javarush.island.ivanilov.entities.BreedingParams;
import com.javarush.island.ivanilov.entities.Terrain;
import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.game.Cell;
import com.javarush.island.ivanilov.game.Settings;
import com.javarush.island.ivanilov.utils.Dice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public abstract class Animal extends Creature
        implements Eating, Movable, Cloneable {
    public static final double MIN_CHANCE_TO_BORN_FEMALE = 0.5;
    private String name;
    private double weight;
    protected boolean isFemale;
    protected boolean isPregnant = false;
    protected AtomicInteger pregnancyCounter = new AtomicInteger(0);
    private AnimalLimits animalLimits;
    private BreedingParams breedingParams;
    private Map<Type, Integer> foodPreferences;
    protected Set<Terrain> terrains = new HashSet<>();
    private ReentrantLock lock = new ReentrantLock();
    private int carrionDivider;

    @Override
    public void reproduce(Creature creature) {
        Animal animal = (Animal) creature;

        if (this.isFemale())
            throw new IslandGameException("Animal is not male");

        if (!animal.isFemale())
            throw new IslandGameException("Target is not female");

        double breedingChance = Dice.random(0.0, 1.0);

        if (breedingChance < 0.5)
            return;

        animal.getLock().lock();
        animal.setPregnant(true);
        animal.getPregnancyCounter().set(animal.getBreedingParams().getPregnancyLength());
        animal.getLock().unlock();
    }

    @Override
    public boolean move(Cell cell) {
        return this.getTerrains().contains(cell.getTerrain());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", isFemale=" + this.isFemale() +
                '}';
    }

    @Override
    public Animal clone() {
        try {
            Animal animal = (Animal) super.clone();
            int id = idCounter.getAndIncrement();
            animal.setId(id);
            String name = animal.getName();
            animal.setName(name + id);
            double minWeight = animal.getAnimalLimits().getMinWeight();
            double amountOfFoodNeeded = animal.getAnimalLimits().getAmountOfFoodNeeded();
            animal.setWeight(minWeight + Dice.random((amountOfFoodNeeded / 2), amountOfFoodNeeded));
            double genderChance = Dice.random(0.0, 1.0);
            animal.setFemale(genderChance > MIN_CHANCE_TO_BORN_FEMALE);
            animal.setPregnant(false);
            animal.setPregnancyCounter(new AtomicInteger(0));
            return animal;
        } catch (CloneNotSupportedException e) {
            throw new IslandGameException(e);
        }
    }

    @Override
    public void eat(Creature creature, Cell cell) {

        boolean isEatable = getFoodPreferences().containsKey(creature.getClass());
        if (isEatable) {
            if (creature instanceof Plant plant) {
                eatPlant(plant);
                return;
            }

            if (creature instanceof Animal animal) {
                eatAnimal(animal, cell);
            }
        }

    }

    private void eatAnimal(Animal animal, Cell cell) {
        double weight = this.getWeight();
        double maxWeight = this.getAnimalLimits().getMaxWeight();
        double amountOfFoodNeeded = maxWeight - weight;
        double targetWeight = animal.getWeight();
        cell.getLock().lock();
        double carrionInCell = cell.getCarrion();
        double newCarrion = 0;

        if (targetWeight > amountOfFoodNeeded) {
            this.setWeight(weight + amountOfFoodNeeded);
            newCarrion += (targetWeight - amountOfFoodNeeded) / Settings.settings.CARRION_DIVIDER;
            animal.setWeight(0);
        } else {
            if (targetWeight > 0) {
                this.setWeight(weight + targetWeight);
                animal.setWeight(0);
            }
        }
        cell.setCarrion(carrionInCell + newCarrion);
        cell.getLock().unlock();
    }

    private void eatPlant(Plant plant) {
        double mass = plant.getMass();
        double weight = this.getWeight();
        double maxWeight = this.getAnimalLimits().getMaxWeight();
        double amountOfFoodNeeded = maxWeight - weight;

        if (mass > amountOfFoodNeeded) {
            plant.setMass(mass - amountOfFoodNeeded);
            this.setWeight(weight + amountOfFoodNeeded);
        } else {
            if (mass > 0) {
                plant.setMass(0);
                this.setWeight(weight + mass);
            }
        }
    }

    public void starve() {
        double weight = this.getWeight();
        double starvingTempo = this.getAnimalLimits().getStarvingTempo();
        this.setWeight(weight - starvingTempo);
    }

    public void eatCarrion(Cell cell) {
        if (!this.getClass().isAnnotationPresent(EatingCarrion.class)) {
            throw new IslandGameException("This animal doesn't eat carrion");
        }

        cell.getLock().lock();
        this.getLock().lock();
        double carrion = cell.getCarrion();
        double weight = this.getWeight();
        double amountOfFoodNeeded = this.getAnimalLimits().getAmountOfFoodNeeded();

        if (carrion > amountOfFoodNeeded) {
            cell.setCarrion(carrion - amountOfFoodNeeded);
            this.setWeight(weight + amountOfFoodNeeded);
        }

        if (carrion < amountOfFoodNeeded) {
            cell.setCarrion(0);
            this.setWeight(weight + carrion);
        }
        this.getLock().unlock();
        cell.getLock().unlock();
    }
}
