package com.javarush.island.bogdan.island;

import com.javarush.island.bogdan.entity.Entity;
import com.javarush.island.bogdan.entity.EntityType;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Location {

    private Map<Integer, Set<Entity>> entitiesToLocation;
    private List<Location> nearbyLocations;
    private final Lock lock = new ReentrantLock();

    public Location() {
        this.entitiesToLocation = new HashMap<>();
        this.nearbyLocations = new ArrayList<>();
    }

    public Set<Entity> getOneTypeOfEntitiesToLocation(int typeOfEntityToLocation) {
        return entitiesToLocation.get(typeOfEntityToLocation);
    }

    public void setEntitiesToLocation(Map<Integer, Set<Entity>> entitiesToLocation) {
        this.entitiesToLocation = entitiesToLocation;
    }

    public void clearingResetDayMapEntitiesToLocation() {
        for (Map.Entry<Integer, Set<Entity>> entry : entitiesToLocation.entrySet()) {
            entry.getValue().removeIf(Entity::isRemovable);
            entry.getValue().iterator().forEachRemaining(Entity::itIsNewDay);
        }
    }

    public List<Location> getNearbyLocations() {
        return nearbyLocations;
    }

    public void setNearbyLocations(List<Location> nearbyLocations) {
        this.nearbyLocations = nearbyLocations;
    }

    public void addEntityToLocation(int typeOfEntityToCreate) {
        entitiesToLocation.get(typeOfEntityToCreate).add(Entity.returnNewEntity(typeOfEntityToCreate));
    }

    public Lock getLock() {
        return lock;
    }

    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Set<Entity>> entry : entitiesToLocation.entrySet()) {
            System.out.print(EntityType.values()[entry.getKey()].getIcon() + "-" + entry.getValue().size() + " ");
        }
        return result;
    }
}
