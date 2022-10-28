package com.javarush.island.uzienko.entity.residents;

import com.javarush.island.uzienko.config.Phases;
import com.javarush.island.uzienko.exceptions.IslandException;
import com.javarush.island.uzienko.sevices.GodsWillService;
import com.javarush.island.uzienko.utils.Generator;
import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.storage.Coords;
import lombok.ToString;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ToString
public abstract class Animal implements Resident {
    private static long counter;

    private ResidentProperties properties;
    private GodsWillService godsWillService;
    private long id = 0;
    private final Lock lock = new ReentrantLock(true);

    protected Animal(ResidentProperties properties) {
        this.properties = properties;
    }

    @Override
    public void setGodsWillService(GodsWillService godsWillService) {
        this.godsWillService = godsWillService;
    }

    private void setId() {
        id = ++counter;
    }

    @Override
    public Lock getLock() {
        return lock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public ResidentProperties getProperties() {
        return properties;
    }

    private void setProperties(ResidentProperties properties) {
        this.properties = properties;
    }

    @Override
    public Resident clone() throws CloneNotSupportedException {
        Animal animal = (Animal) super.clone();
        ResidentProperties residentProperties = new ResidentProperties(properties);
        animal.setProperties(residentProperties);
        animal.setId();
        return animal;
    }

    @Override
    public void startPhase(Phases phase) {
        starve();
        switch (phase) {
            case EAT -> eat();
            case MOVE -> move();
            case SPAWN -> spawn();
        }
    }

    void eat() {
        double full = 0d;
        Set<Resident> foodSet = godsWillService.giveFoodForResident(this);
        if (foodSet.isEmpty()) {
            return;
        }

        if (lock.tryLock()) {
            try {
                if (!properties.isDead()) {
                    for (Resident resident : foodSet) {
                        int chance = properties.getFoodList().get(resident.getProperties().getType());
                        int luck = Generator.get(0, 101);

                        if (luck <= chance && resident.getLock().tryLock()) {
                            try {
                                if (!resident.getProperties().isDead() &&
                                        resident.getProperties().getCoords().equals(properties.getCoords())) {
                                    resident.getProperties().setDead(true);
                                    godsWillService.removeResident(resident);
                                    full += resident.getProperties().getCurrentWeight();
                                }
                            } finally {
                                resident.getLock().unlock();
                            }
                        }

                        if (Double.compare(full, properties.getFood()) >= 0) {
                            properties.setCurrentWeight(properties.getCurrentWeight() + properties.getFood());
                            break;
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    void move() {
        if (properties.getSpeed() > 0 && !properties.isDead()) {
            final int steps = Generator.get(0, properties.getSpeed() + 1);
            final Coords coords = godsWillService.whereIShouldGo(properties.getCoords(), steps);

            if (!properties.getCoords().equals(coords) && lock.tryLock()) {
                try {
                    if (!properties.isDead() &&
                            godsWillService.tryToMigrateResident(properties.getCoords(), coords, this)) {
                        properties.setCoords(coords);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    void spawn() {
        if (lock.tryLock()) {
            try {
                if (!properties.isDead()) {
                    godsWillService.giveSpawnChanceForResident(this);
                }
            } catch (CloneNotSupportedException e) {
                throw new IslandException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    private void starve() {
        if (lock.tryLock()) {
            try {
                getProperties().setCurrentWeight(getProperties().getCurrentWeight() - getProperties().getWeight() / 10.);
                if (getProperties().getCurrentWeight() < 0) {
                    properties.setDead(true);
                    godsWillService.removeResident(this);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
