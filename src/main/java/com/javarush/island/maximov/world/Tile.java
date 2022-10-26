package com.javarush.island.maximov.world;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.MapLegend;
import com.javarush.island.maximov.utils.ColorCode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Tile {
    private final int index;
    private final MapLegend mapLegend;
    private final String symbol;
    private final ColorCode colorCode;
    private final List<Tile> movementTile = new ArrayList<>();
    private final Map<FamilyIdSettings, AtomicInteger> numbersOfAnimals = new HashMap<>();
    private final Map<Integer, List<Creature>> listOfAnimals = new HashMap<>();
    private final Map<Integer, AtomicInteger> indexListOfAnimals = new HashMap<>();

    public Tile(int index, MapLegend mapLegend, String symbol, ColorCode colorCode) {
        this.index = index;
        this.mapLegend = mapLegend;
        this.symbol = symbol;
        this.colorCode = colorCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public MapLegend getMapLegend() {
        return mapLegend;
    }

    public int getIndex() {
        return index;
    }

    public ColorCode getColorCode() {
        return colorCode;
    }

    public boolean isReproducing() {
        return switch (mapLegend) {
            case DEFAULT, MOUNTAIN, RIVER -> false;
            case FIELD -> true;
        };
    }

    public boolean isMoving() {
        return switch (mapLegend) {
            case DEFAULT, MOUNTAIN -> false;
            case RIVER, FIELD -> true;
        };
    }

    public List<Tile> getMovementTile() {
        return movementTile;
    }

    public Map<FamilyIdSettings, AtomicInteger> getNumbersOfAnimals() {
        return numbersOfAnimals;
    }

    public void incrementAnimalNumbers(FamilyIdSettings familyIdSettings) {
        numbersOfAnimals.get(familyIdSettings).incrementAndGet();
    }

    public void decrementAnimalNumbers(FamilyIdSettings familyIdSettings) {
        numbersOfAnimals.get(familyIdSettings).decrementAndGet();
    }

    public void decrementIndexListOfAnimals(int familyIdNumber) {
        indexListOfAnimals.get(familyIdNumber).decrementAndGet();
    }

    public void loadEmptyFamilyIdSettings(List<FamilyIdSettings> familyIdSettingsList) {
        familyIdSettingsList.forEach(familyIdSettings -> {
            numbersOfAnimals.put(familyIdSettings, new AtomicInteger());
            listOfAnimals.put(familyIdSettings.getFamilyIdNumber(), new ArrayList<>());
            indexListOfAnimals.put(familyIdSettings.getFamilyIdNumber(), new AtomicInteger());
        });

    }

    public Map<Integer, List<Creature>> getListOfAnimals() {
        return listOfAnimals;
    }

    public Map<Integer, AtomicInteger> getIndexListOfAnimals() {
        return indexListOfAnimals;
    }

    public void addAnimalToList(int familyIdNumber, Creature creature) {
        listOfAnimals.get(familyIdNumber).add(creature);
    }

    public void clearAnimalsList() {
        for (List<Creature> creatureList : listOfAnimals.values()) {
            creatureList.clear();
        }
        for (AtomicInteger indexForList : indexListOfAnimals.values()) {
            indexForList.set(-1);
        }
    }

    public void shuffleAndCountAnimalsList() {
        for (Map.Entry<Integer, List<Creature>> familyIdAnimalList : listOfAnimals.entrySet()) {
            if (!familyIdAnimalList.getValue().isEmpty()) {
                Collections.shuffle(familyIdAnimalList.getValue());
                indexListOfAnimals.get(familyIdAnimalList.getKey()).set(familyIdAnimalList.getValue().size() - 1);
            }
        }
    }
}