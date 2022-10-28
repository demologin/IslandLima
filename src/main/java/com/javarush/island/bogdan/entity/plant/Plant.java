package com.javarush.island.bogdan.entity.plant;

import com.javarush.island.bogdan.config.Config;
import com.javarush.island.bogdan.entity.Entity;
import com.javarush.island.bogdan.entity.EntityType;
import com.javarush.island.bogdan.island.Location;

public class Plant extends Entity {
    public Plant() {
        super(EntityType.PLANTS);
    }

    @Override
    public void multiply(Location currentLocation) {
        safeMultiply(currentLocation);
    }

    @Override
    public boolean eat(Location currentLocation) {
        return false;
    }

    @Override
    public void move(Location currentLocation) {
    }

    private void safeMultiply(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            int plantsToLocationQuantity = currentLocation.getOneTypeOfEntitiesToLocation(getTypeInt()).size();
            int maxQuantityInLocation = Config.MAX_PER_LOCATION[getTypeInt()];
            if (plantsToLocationQuantity < maxQuantityInLocation) {
                currentLocation.addEntityToLocation(getTypeInt());
            }
        } finally {
            currentLocation.getLock().unlock();
        }
    }
}
