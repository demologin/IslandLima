package com.javarush.island.uzienko.entity.residents;

import com.javarush.island.uzienko.config.Phases;
import com.javarush.island.uzienko.entity.properties.ResidentProperties;
import com.javarush.island.uzienko.sevices.GodsWillService;

import java.util.concurrent.locks.Lock;

public interface Resident extends Cloneable {
    ResidentProperties getProperties();

    Resident clone() throws CloneNotSupportedException;

    void setGodsWillService(GodsWillService godsWillService);

    void startPhase(Phases phase);

    Lock getLock();
}
