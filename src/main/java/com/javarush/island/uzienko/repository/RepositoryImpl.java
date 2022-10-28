package com.javarush.island.uzienko.repository;

import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.storage.Storage;
import com.javarush.island.uzienko.storage.Coords;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class RepositoryImpl implements Repository {
    public final Storage storage;

    @Override
    public Map<String, Set<Resident>> getResidentsByCoords(Coords coords) {
        return storage.copyNodeData(coords);
    }

    @Override
    public Integer getHeardSize(Coords coords, String type) {
        if (coords == null || type == null) {
            return 0;
        }
        return storage.getHeardSize(coords, type);
    }

    @Override
    public boolean addResidentToCoords(Coords coords, Resident resident) {
        return storage.addResidentToNode(coords, resident);
    }

    @Override
    public boolean removeResident(Resident resident) {
        return storage.removeResidentFromNode(resident.getProperties().getCoords(), resident);
    }

    @Override
    public boolean removeResident(Coords coords, Resident resident) {
        return storage.removeResidentFromNode(coords, resident);
    }

    @Override
    public void addAll(Coords coords, Set<Resident> residentSet) {
        storage.addAll(coords, residentSet);
    }

    @Override
    public Set<Resident> getAll(Coords coords, Set<String> types) {
        return storage.getAll(coords, types);
    }

    @Override
    public boolean tryToMigrateResident(Coords oldCoords, Coords newCoords, Resident resident) {
        return storage.tryToMigrateResident(oldCoords, newCoords, resident);
    }
}
