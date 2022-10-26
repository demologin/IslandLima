package com.javarush.island.ivanilov.creatures;

import com.javarush.island.ivanilov.behaviours.Reproducible;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Creature implements Reproducible {
    public static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String icon;
    private ReentrantLock lock = new ReentrantLock();
}
