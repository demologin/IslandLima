package com.javarush.island.alimin.entity.location;

import com.javarush.island.alimin.entity.ecosystem.Organism;
import com.javarush.island.alimin.entity.ecosystem.OrganismFactory;
import com.javarush.island.alimin.entity.ecosystem.Types;
import com.javarush.island.alimin.properties.OrganismParameters;
import com.javarush.island.alimin.properties.Properties;
import com.javarush.island.alimin.utils.Random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class Location {

    private static volatile Location instance;
    private final Cell[][] location = new Cell[Properties.LOCATION_SIZE_ROW][Properties.LOCATION_SIZE_COLUMN];

    private Location() {
    }

    public static Location getInstance() {
        Location result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Location.class) {
            if (instance == null) {
                instance = new Location();
            }
            return instance;
        }
    }

    public Cell[][] getLocation() {
        return location;
    }

    public void initialize() {
        for (int i = 0; i < location.length; i++) {
            for (int j = 0; j < location[0].length; j++) {
                Cell cell = new Cell(i, j);
                location[i][j] = cell;
                boolean isFill = Random.getProbability(Properties.FILL_PROBABILITY);
                if (isFill) {
                    Map<Types, Set<Organism>> residents = cell.getResidents();
                    for (Map.Entry<Types, OrganismParameters> entry : Properties.organismProperties.entrySet()) {
                        boolean isBorn;
                        if (entry.getKey() != Types.PLANT) {
                            isBorn = Random.getProbability(Properties.BORN_PROBABILITY);
                        } else {
                            isBorn = true;
                        }
                        if (isBorn) {
                            residents.put(entry.getKey(), getSet(entry));
                        }
                    }
                }
            }
        }
    }

    private Set<Organism> getSet(Map.Entry<Types, OrganismParameters> entry) {
        Set<Organism> result = new HashSet<>();
        Types type = entry.getKey();
        OrganismParameters parameters = entry.getValue();
        int maxCount = parameters.getMaxCount();
        for (int i = 0; i < maxCount; i++) {
            boolean isBorn;
            if (type == Types.PLANT) {
                isBorn = true;
            } else {
                isBorn = Random.getProbability(Properties.BORN_PROBABILITY);
            }
            if (isBorn) {
                Organism organism = OrganismFactory.getInstance().createOrganism(type, parameters);
                result.add(organism);
            }
        }
        return result;
    }

    public void printLocationState() {
        for (Cell[] cells : location) {
            for (int j = 0; j < location[0].length; j++) {
                System.out.print(cells[j] + "\t");
                Set<Map.Entry<Types, Set<Organism>>> entries = cells[j].getResidents().entrySet();
                System.out.print("{");
                for (Map.Entry<Types, Set<Organism>> entry : entries) {
                    String organismIcon = "";
                    int counter = 0;
                    Set<Organism> organisms = entry.getValue();
                    if (organisms.size() != 0) {
                        for (Organism organism : organisms) {
                            organismIcon = organism.getParameters().getIcon();
                            counter++;
                        }
                        if (!organismIcon.equals("")) {
                            System.out.print(organismIcon + " - " + counter + " шт." + "\t");
                        }
                    }
                }
                System.out.print("}");
                System.out.println();
            }
            System.out.println();
        }
    }

    public int printStatistics() {
        AtomicInteger counter = new AtomicInteger();
        Map<String, Integer> statistics = new HashMap<>();
        String name = "";
        for (Cell[] cells : location) {
            for (int j = 0; j < location[0].length; j++) {
                Set<Map.Entry<Types, Set<Organism>>> entries = cells[j].getResidents().entrySet();
                for (Map.Entry<Types, Set<Organism>> entry : entries) {
                    Set<Organism> organisms = entry.getValue();
                    for (Organism organism : organisms) {
                        name = organism.getParameters().getIcon();
                        break;
                    }
                    if (statistics.containsKey(name)) {
                        Integer value = statistics.get(name);
                        statistics.put(name, value + organisms.size());
                    } else {
                        if (!name.equals("")) {
                            statistics.put(name, organisms.size());
                        }
                    }
                }
            }
        }
        printBar(statistics);
        printInfo(counter, statistics);
        return statistics.size();
    }

    private void printBar(Map<String, Integer> statistics) {
        Map<String, Integer> statisticsCopy = new HashMap<>(statistics);
        if (statisticsCopy.size() == 0) {
            return;
        }
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : statisticsCopy.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        if (maxEntry != null) {
            System.out.println(maxEntry.getKey() + ": " + "*".repeat(statisticsCopy.size()));
            statisticsCopy.remove(maxEntry.getKey());
            printBar(statisticsCopy);
        }
    }

    private void printInfo(AtomicInteger counter, Map<String, Integer> statistics) {
        statistics.forEach((s, integer) -> counter.addAndGet(integer));
        System.out.println("Всего организмов на острове: " + counter);
        if (statistics.size() != 0) {
            System.out.print("Из них: ");
            statistics.forEach((s, integer) -> System.out.print(s + " - " + integer + " шт. "));
            System.out.println();
        }
        System.out.println("*".repeat(175));
    }
}