package com.javarush.island.uzienko.storage;

import com.javarush.island.uzienko.config.GameConfig;
import com.javarush.island.uzienko.entity.residents.Resident;
import com.javarush.island.uzienko.exceptions.IslandException;

import java.util.*;

public class StorageImpl implements Storage {
    private final Node[][] storage;
    private final GameConfig config;

    public StorageImpl(GameConfig config) {
        this.config = config;
        storage = new Node[config.getCols()][config.getRows()];
        init();
    }

    private void init() {
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getCols(); j++) {
                storage[j][i] = new Node();
            }
        }
    }

    @Override
    public Map<String, Set<Resident>> copyNodeData(Coords coords) {
        if (coords == null || storage[coords.getX()][coords.getY()] == null) {
            return new HashMap<>();
        }
        return new HashMap<>(get(coords).getResidents());
    }

    @Override
    public boolean addResidentToNode(Coords coords, Resident resident) {
        if (coords == null || resident == null || storage[coords.getX()][coords.getY()] == null) {
            return false;
        }
        return get(coords).add(resident);
    }

    @Override
    public boolean removeResidentFromNode(Coords coords, Resident resident) {
        if (coords == null || resident == null || storage[coords.getX()][coords.getY()] == null) {
            return false;
        }
        return get(coords).remove(resident);
    }

    @Override
    public void addAll(Coords coords, Set<Resident> residentSet) {
        get(coords).addAll(residentSet);
    }

    @Override
    public Integer getHeardSize(Coords coords, String type) {
        return get(coords).getResidents().get(type).size();
    }

    @Override
    public Set<Resident> getAll(Coords coords, Set<String> types) {
        if (coords == null || types == null || types.size() == 0) {
            return new HashSet<>();
        }
        return get(coords).getAll(types);
    }

    @Override
    public boolean tryToMigrateResident(Coords oldCoords, Coords newCoords, Resident resident) {
        Node oldNode = get(oldCoords);
        Node newNode = get(newCoords);
        if (Objects.isNull(oldNode) || Objects.isNull(newNode) || oldCoords.equals(newCoords)) {
            return false;
        }

        String type = resident.getProperties().getType();
        int maxInCell = resident.getProperties().getMaxInCell();
        if (oldNode.getLock().tryLock()) {
            try {
                if (newNode.getLock().tryLock()) {
                    try {
                        if (newNode.getResidents().get(type).size() + 1 < maxInCell) {
                            if (newNode.add(resident)) {
                                if (oldNode.remove(resident)) {
                                    return true;
                                } else {
                                    if (newNode.remove(resident)) {
                                        return false;
                                    } else {
                                        throw new IslandException();
                                    }
                                }
                            }
                        }
                    } finally {
                        newNode.getLock().unlock();
                    }
                }
            } finally {
                oldNode.getLock().unlock();
            }
        }
        return false;
    }

    private Node get(Coords coords) {
        return storage[coords.getX()][coords.getY()];
    }


}
