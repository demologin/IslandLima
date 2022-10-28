package com.javarush.island.uzienko.sevices;

import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.config.Phases;
import com.javarush.island.uzienko.storage.Coords;

import java.util.Map;
import java.util.Set;

public interface GodsWillService {

    void createTheIsland();

    Resident createResident(Coords coords, Resident resident) throws CloneNotSupportedException;

    @SuppressWarnings("UnusedReturnValue")
    boolean removeResident(Resident resident);

    Map<String, Set<Resident>> getResidents(Coords coords);

    Phases whatIShouldToDo(Resident resident);

    Set<Resident> giveFoodForResident(Resident resident);

    void giveSpawnChanceForResident(Resident resident) throws CloneNotSupportedException;

    Coords whereIShouldGo(Coords cords, int steps);

    boolean tryToMigrateResident(Coords oldCoords, Coords newCoords, Resident resident);
}
