package com.javarush.island.bogdan.entity.animal;

import com.javarush.island.bogdan.config.Config;
import com.javarush.island.bogdan.entity.Entity;
import com.javarush.island.bogdan.entity.EntityType;
import com.javarush.island.bogdan.island.Location;
import com.javarush.island.bogdan.utils.Randomizer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Animal extends Entity {
    private int saturation;
    private boolean canMove;

    private boolean canMultiply;

    protected Animal(EntityType type) {
        super(type);
        this.canMove = false;
        this.canMultiply = false;
    }

    @Override
    public void itIsNewDay() {
        this.canMove = true;
        this.canMultiply = true;
        saturation = 0;
        super.itIsNewDay();
    }

    public void canNotMove() {
        this.canMove = false;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanMultiply() {
        return canMultiply;
    }

    public void setCanMultiply() {
        this.canMultiply = false;
    }

    private int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = Math.min(this.saturation + saturation, Config.MAX_SATURATION[this.getTypeInt()]);
    }

    private Set<Entity> makeMenu(Animal animalEater, Location currentLocation) {
        Set<Entity> result = new HashSet<>() {
        };
        for (int entityTypeForFood = 0; entityTypeForFood < EntityType.values().length; entityTypeForFood++) {
            if (animalEater.onTheMenu(entityTypeForFood)) {
                result.addAll(currentLocation.getOneTypeOfEntitiesToLocation(entityTypeForFood));
            }
        }
        return result;
    }

    @Override
    public void multiply(Location currentLocation) {
        safeMultiply(currentLocation);
    }

    private void safeMultiply(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            long sameAnimalTypeQuantityIsMultiply = currentLocation.getOneTypeOfEntitiesToLocation(getTypeInt()).stream().filter(entity ->
                    entity instanceof Animal).filter(entity -> ((Animal) entity).isCanMultiply()).count();
            int maxQuantityInLocation = Config.MAX_PER_LOCATION[getTypeInt()];
            int sameAnimalTypeQuantity = currentLocation.getOneTypeOfEntitiesToLocation(getTypeInt()).size();

            boolean locationFull = (maxQuantityInLocation - sameAnimalTypeQuantity) == 0;
            if (sameAnimalTypeQuantityIsMultiply > 1 && locationFull && Randomizer.getRandomBoolean(50)) {
                currentLocation.addEntityToLocation(getTypeInt());
                this.setCanMultiply();
            }
        } finally {
            currentLocation.getLock().unlock();
        }
    }

    public boolean eat(Location currentLocation) {
        return safeEat(currentLocation);
    }

    private boolean safeEat(Location currentLocation) {
        currentLocation.getLock().lock();
        boolean ate = false;
        try {
            Set<Entity> menu = makeMenu(this, currentLocation);
            for (Entity entity : menu) {
                int chance = Config.CHANCES_TO_EAT[this.getTypeInt()][entity.getTypeInt()];
                if (Randomizer.getRandomBoolean(chance)) {
                    entity.setRemovable();
                    setSaturation(entity.getWeight());
                }
                if (this.getSaturation() == Config.MAX_SATURATION[this.getTypeInt()]) {
                    entity.setRemovable();
                    ate = true;
                    break;
                }
            }
        } finally {
            currentLocation.getLock().unlock();
        }
        return ate;
    }

    public void move(Location currentLocation) {
        safeMove(currentLocation);
    }

    private void safeMove(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            int speed = getSpeed(this.getTypeInt());
            if (speed > 0 && isCanMove()) {
                Location destinationLocation = getNextLocation(currentLocation, speed);
                if (destinationLocation == currentLocation) {
                    return;
                }
                Set<Entity> currentLocationEntities = currentLocation.getOneTypeOfEntitiesToLocation(this.getTypeInt());

                currentLocationEntities.remove(this);

                Set<Entity> destinationLocationEntities = destinationLocation.getOneTypeOfEntitiesToLocation(this.getTypeInt());
                destinationLocationEntities.add(this);
                this.canNotMove();
            }

        } finally {
            currentLocation.getLock().unlock();
        }
    }

    private Location getNextLocation(Location currentLocation, int currentSpeed) {
        List<Location> nearbyLocations = currentLocation.getNearbyLocations();
        if (currentSpeed > 0) {
            int nextLocationIndex = Randomizer.getRandom(0, nearbyLocations.size());
            int loopCount = 0;

            while (true) {
                int quantityAnimalsThisType = currentLocation.getNearbyLocations().get(nextLocationIndex).getOneTypeOfEntitiesToLocation(this.getTypeInt()).size();
                if (quantityAnimalsThisType <= Config.MAX_PER_LOCATION[this.getTypeInt()] ||
                        loopCount >= currentLocation.getNearbyLocations().size()) {
                    break;
                }
                loopCount++;
                nextLocationIndex = Randomizer.getRandom(0, nearbyLocations.size() - 1);
            }
            return getNextLocation(nearbyLocations.get(nextLocationIndex), currentSpeed - 1);
        } else {
            return currentLocation;
        }
    }

    private int getSpeed(int type) {
        return Config.TRAVEL_DISTANCE[type];
    }
}