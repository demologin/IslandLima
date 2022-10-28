package com.javarush.island.uzienko.entity.residents;

import com.javarush.island.uzienko.config.Phases;
import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.exceptions.IslandException;
import com.javarush.island.uzienko.sevices.GodsWillService;
import lombok.ToString;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ToString
public abstract class Plant implements Resident {
    private static long counter;

    private ResidentProperties properties;
    private long id = 0;
    private GodsWillService godsWillService;
    private final Lock lock = new ReentrantLock(true);

    protected Plant(ResidentProperties properties) {
        this.properties = properties;
    }

    private void setId() {
        id = --counter;
    }

    @Override
    public Lock getLock() {
        return lock;
    }

    @Override
    public void setGodsWillService(GodsWillService godsWillService) {
        this.godsWillService = godsWillService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id;
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
        Plant plant = (Plant) super.clone();
        ResidentProperties residentProperties = new ResidentProperties(properties);
        plant.setProperties(residentProperties);
        plant.setId();
        return plant;
    }

    @Override
    public void startPhase(Phases phase) {
        if (phase.equals(Phases.SPAWN)) {
            spawn();
        }
    }

    void spawn() {
        lock.lock();
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
