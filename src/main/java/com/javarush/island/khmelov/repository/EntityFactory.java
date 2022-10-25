package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.Residents;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.entity.organizms.Organisms;
import com.javarush.island.khmelov.util.Rnd;

import java.util.Arrays;
import java.util.List;

public class EntityFactory implements Factory {

    public static final int PERCENT_FILL = 33;
    public static final int PERCENT_PROBABLY_BORN = 50;

    @Override
    public Cell createRandomCell(boolean empty) {
        Cell cell = new Cell();
        Residents residents = cell.getResidents();
        boolean fill = Rnd.get(PERCENT_FILL);
        if (fill && !empty) {
            for (Organism prototype : Setting.PROTOTYPES) {
                String type = prototype.getType();
                boolean born = Rnd.get(PERCENT_PROBABLY_BORN);
                if (born) {
                    Organisms organisms = residents.get(type);
                    int currentCount = organisms.size();
                    int max = prototype.getLimit().getMaxCountInCell() - currentCount;
                    int count = Rnd.random(0, max);
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
