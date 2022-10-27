package com.javarush.island.torkel.entity.plant;

import com.javarush.island.torkel.entity.Entity;
import com.javarush.island.torkel.entity.EntityType;

public class Plant extends Entity {

    public Plant(EntityType type) {
        super(type);
    }

    @Override
    public Entity multiply() {
        return Entity.createEntity(this.getType());
    }

    @Override
    public void changeWeight() {

    }


}
