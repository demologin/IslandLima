package com.javarush.island.bogdan.servise;

import com.javarush.island.bogdan.entity.Entity;
import com.javarush.island.bogdan.entity.animal.Animal;
import com.javarush.island.bogdan.island.Location;

public class Task {
    private final Entity entity;
    private final Location location;

    public Task(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    public void perform() {
        if (entity instanceof Animal animal) {
            if (animal.eat(location)) {
                animal.multiply(location);
            }
            animal.move(location);
        } else {
            entity.multiply(location);
        }
    }

}
