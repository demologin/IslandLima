package com.javarush.island.uzienko.repository;

import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.storage.Coords;

import java.util.Map;
import java.util.Set;

public interface Repository {
    Map<String, Set<Resident>> getResidentsByCoords(Coords coords);

    Integer getHeardSize(Coords coords, String type);

    @SuppressWarnings("UnusedReturnValue")
    boolean addResidentToCoords(Coords coords, Resident resident);

    boolean removeResident(Resident resident);

    boolean removeResident(Coords coords, Resident resident);

    void addAll(Coords coords, Set<Resident> residentSet);

    Set<Resident> getAll(Coords coords, Set<String> types);

    boolean tryToMigrateResident(Coords oldCoords, Coords newCoords, Resident resident);
}
