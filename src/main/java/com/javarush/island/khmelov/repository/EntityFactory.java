package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.util.Probably;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityFactory implements Factory {

    @Override
    public Cell createRandomCell(boolean empty) {
        Cell cell = new Cell();
        Map<String, Set<Organism>> residents = cell.getResidents();
        boolean fill = Probably.get(33); //TODO need config
        if (fill && !empty) {
            for (Organism prototype : Setting.PROTOTYPES) {
                String type = prototype.getType();
                boolean born = Probably.get(50); //TODO need config
                if (born) {
                    Set<Organism> organisms = residents.get(type);
                    int currentCount = organisms.size();
                    int max = prototype.getLimit().getMaxCount() - currentCount;
                    int count = Probably.random(0, max);
                    for (int i = 0; i < count; i++) {
                        organisms.add(Organism.clone(prototype));
                    }
                }
            }
        }
        return cell;
    }

    @Override
    public List<Organism> getAllPrototypes() {
        return Arrays.asList(Setting.PROTOTYPES);
    }

    @Override
    public String toString() {
        return "EntityFactory{" +
                "prototypes=" + Arrays.toString(Setting.PROTOTYPES) +
                '}';
    }
}
