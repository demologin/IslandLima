package com.javarush.island.khmelov.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityFactoryTest {

    @Test
    void init() {
        EntityFactory entityFactory = new EntityFactory();
        Assertions.assertTrue(entityFactory.toString().contains("⾋,"));
    }
}