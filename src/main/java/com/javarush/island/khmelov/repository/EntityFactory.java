package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.entity.organizms.animals.herbivores.Horse;
import com.javarush.island.khmelov.entity.organizms.animals.predators.Wolf;
import com.javarush.island.khmelov.entity.organizms.plants.Plant;
import com.javarush.island.khmelov.util.EntityFactoryData;
import com.javarush.island.khmelov.util.Probably;

import java.lang.reflect.Type;
import java.util.*;

public class EntityFactory implements Factory {

    private static final Class<?>[] TYPES = {Plant.class, Wolf.class, Horse.class};
    private static final Organism[] PROTOTYPES = EntityFactoryData.createPrototypes(TYPES);

    public EntityFactory() {

    }

    @Override
    public Cell createRandomCell() {
        Map<Type, Set<Organism>> residents = new HashMap<>();
        boolean fill = Probably.get(50); //TODO need config
        if (fill) {
            for (Organism prototype : PROTOTYPES) {
                Type type = prototype.getClass();
                boolean born = Probably.get(50); //TODO need config
                if (born) {
                    residents.putIfAbsent(type, new HashSet<>());
                    Set<Organism> organisms = residents.get(prototype.getClass());
                    int currentCount = organisms.size();
                    int max = prototype.getLimit().getMaxCount() - currentCount;
                    int count = Probably.random(0, max);
                    for (int i = 0; i < count; i++) {
                        organisms.add(prototype.clone());
                    }

                }
            }
        }
        Cell cell = new Cell(residents);
        cell.setNextCell(new ArrayList<>());
        return cell;
    }

    @Override
    public List<Organism> getAllPrototypes() {
        return Arrays.asList(PROTOTYPES);
    }

    @Override
    public String toString() {
        return "EntityFactory{" +
                "prototypes=" + Arrays.toString(PROTOTYPES) +
                '}';
    }
}
