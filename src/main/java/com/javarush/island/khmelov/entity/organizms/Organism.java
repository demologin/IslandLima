package com.javarush.island.khmelov.entity.organizms;

import com.javarush.island.khmelov.abstraction.entity.Reproducible;
import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("unused")
@Getter
@Setter
@AllArgsConstructor
public abstract class Organism implements Reproducible, Cloneable {

    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    private final long id = idCounter.incrementAndGet();
    private String name;
    private String icon;
    private double weight;
    private Limit limit;

    @Override
    public Organism clone() {
        try {
            return (Organism) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("cannot clone " + this);
        }
    }

    @Override
    public String toString() {
        return icon;
    }
}
