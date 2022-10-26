package com.javarush.island.uzienko.storage;

import com.javarush.island.uzienko.entity.residents.Resident;

import java.util.Map;
import java.util.Set;

public interface Storage {
    Map<String, Set<Resident>> copyNodeData(Coords coords);

    boolean addResidentToNode(Coords coords, Resident resident);

    boolean removeResidentFromNode(Coords coords, Resident resident);

    void addAll(Coords coords, Set<Resident> residentSet);

    Integer getHeardSize(Coords coords, String type);

    Set<Resident> getAll(Coords coords, Set<String> types);

    boolean tryToMigrateResident(Coords oldCoords, Coords newCoords, Resident resident);
}
