package com.javarush.island.uzienko.storage;

import com.javarush.island.uzienko.entity.residents.Resident;
import lombok.Getter;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Node {
    private final Map<String, Set<Resident>> residents = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock(true);

    public boolean add(Resident resident) {
        String type = resident.getProperties().getType();
        var set = residents.get(type);
        if (Objects.isNull(set)) {
            set = new CopyOnWriteArraySet<>();
            residents.put(type, set);
        }
        set.add(resident);
        return true;
    }

    public boolean remove(Resident resident) {
        String type = resident.getProperties().getType();
        var set = residents.get(type);
        if (Objects.isNull(set)) {
            return false;
        }
        return set.remove(resident);
    }

    public void addAll(Set<Resident> residentSet) {
        if (Objects.nonNull(residentSet) && !residentSet.isEmpty()) {
            String type = residentSet.stream().findAny().get().getProperties().getType();
            residents.putIfAbsent(type, residentSet);
        }
    }

    public Set<Resident> getAll(Set<String> types) {
        Set<Resident> result = new HashSet<>();
        for (String type : types) {
            Set<Resident> all = residents.get(type);
            if (Objects.nonNull(all) && all.size() > 0) {
                result.addAll(residents.get(type));
            }
        }
        return result;
    }
}
