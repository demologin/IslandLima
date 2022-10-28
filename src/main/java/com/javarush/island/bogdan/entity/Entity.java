package com.javarush.island.bogdan.entity;

import com.javarush.island.bogdan.config.Config;
import com.javarush.island.bogdan.entity.animal.herbivore.*;
import com.javarush.island.bogdan.entity.animal.predator.*;
import com.javarush.island.bogdan.entity.plant.Plant;
import com.javarush.island.bogdan.island.Location;

import java.util.Objects;

public abstract class Entity {
    private static long totalCounter = 0;
    private final long count;
    private final EntityType type;
    private boolean removable;
    private final int weight;
    private int age;

    public abstract void multiply(Location currentLocation);

    public abstract boolean eat(Location currentLocation);

    public abstract void move(Location currentLocation);

    public static Entity returnNewEntity(int random) {
        return switch (random) {
            case 0 -> new Bear();
            case 1 -> new Boa();
            case 2 -> new Eagle();
            case 3 -> new Fox();
            case 4 -> new Wolf();
            case 5 -> new Boar();
            case 6 -> new Buffalo();
            case 7 -> new Caterpillar();
            case 8 -> new Deer();
            case 9 -> new Duck();
            case 10 -> new Goat();
            case 11 -> new Horse();
            case 12 -> new Mouse();
            case 13 -> new Rabbit();
            case 14 -> new Sheep();
            default -> new Plant();
        };
    }

    protected Entity(EntityType type) {
        this.type = type;
        this.removable = false;
        this.weight = Config.ENTITY_WEIGHT[type.ordinal()];
        this.age = 0;
        this.count = ++totalCounter;
    }

    public int getTypeInt() {
        return type.ordinal();
    }

    public void setRemovable() {
        this.removable = true;
    }

    public boolean isRemovable() {
        return removable;
    }

    public int getWeight() {
        return weight;
    }

    public boolean onTheMenu(int entityForFoodType) {
        return Config.CHANCES_TO_EAT[this.getTypeInt()][entityForFoodType] != 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        ++this.age;
    }

    public void itIsNewDay() {
        setAge();
        if (getAge() > Config.MAX_AGE_ENTITIES[getTypeInt()]) {
            this.setRemovable();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return count == entity.count && type == entity.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, type);
    }

    @Override
    public String toString() {
        return type.getIcon();
    }
}
