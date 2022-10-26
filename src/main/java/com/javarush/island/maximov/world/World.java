package com.javarush.island.maximov.world;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.creature.CreatureFactory;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.GlobalSettings;
import com.javarush.island.maximov.settings.MapLegend;
import com.javarush.island.maximov.utils.ColorCode;
import com.javarush.island.maximov.utils.Logger;
import com.javarush.island.maximov.utils.Randomizer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class World {
    private final Logger logger;
    private final GlobalSettings globalSettings;
    private final AtomicLong indexNumberOfCreatures = new AtomicLong(0);
    private final List<FamilyIdSettings> familyIdSettingsList = new ArrayList<>();
    private final Map<Long, Creature> creatureList = new HashMap<>();
    private final List<Creature> creatureWaitList = Collections.synchronizedList(new ArrayList<>());

    private final Map<FamilyIdSettings, AtomicInteger> worldNumbersOfAnimals = new HashMap<>();
    private final Map<FamilyIdSettings, Integer> originalNumbersOfAnimals = new HashMap<>();
    private final Map<Integer, Tile> mapList = new HashMap<>();
    private final AtomicInteger turn = new AtomicInteger(0);

    public World(Logger logger, GlobalSettings globalSettings) {
        this.logger = logger;
        this.globalSettings = globalSettings;
    }

    public void startGenerating(int lowerNumberOfAnimals) {
        logger.logMessage("Creating a map...", false);
        createMap();
        logger.logMessage("Calculating pathways...", false);
        generatePathwaysForMap();
        logger.logMessage("Loading creatures...", false);
        loadFamilyIdSettings();
        logger.logMessage("Fill tiles with creatures...", false);
        fillAllTilesWithCreature(lowerNumberOfAnimals);
        copyNumberOfAnimalsToOriginal();

        logger.logMessage("Island generation finished.\n  - Map tiles: "
                + Statistics.DECIMAL_FORMAT.format(mapList.size())
                + "\n  - Types of creatures: " + familyIdSettingsList.size()
                + "\n  - Total creatures: " + Statistics.DECIMAL_FORMAT.format(indexNumberOfCreatures), false);
    }

    private void loadEmptyFamilyIdSettingsToWorld() {
        familyIdSettingsList.forEach(familyIdSettings
                -> worldNumbersOfAnimals.put(familyIdSettings, new AtomicInteger()));
    }

    private void loadEmptyFamilyIdSettingsToTiles() {
        mapList.values().forEach(tile -> tile.loadEmptyFamilyIdSettings(familyIdSettingsList));
    }

    private MapLegend getMapLegendFromSymbol(String symbol) {
        if (!globalSettings.getMapLegendRevers().containsKey(symbol)) {
            logger.logMessage("Map type '" + symbol + "' not found.", true);
            return MapLegend.DEFAULT;
        }
        return globalSettings.getMapLegendRevers().get(symbol);
    }

    private String getMapSymbolFromLegend(MapLegend mapLegend) {
        if (!globalSettings.getMapLegend().containsKey(mapLegend)) {
            logger.logMessage("Map type '" + mapLegend + "' symbol not found.", true);
            return globalSettings.getMapLegend().get(MapLegend.DEFAULT);
        }
        return globalSettings.getMapLegend().get(mapLegend);
    }

    private void createMap() {
        globalSettings.createMapLegendRevers();
        int index = 0;
        index = generateMultiMapTitle(index, globalSettings.getMapSizeWidth() + 2, MapLegend.DEFAULT);
        for (Integer rowNumber : globalSettings.getMap().keySet()) {
            index = generateMultiMapTitle(index, 1, MapLegend.DEFAULT);
            String rowMap = globalSettings.getMap().get(rowNumber);
            for (char oneTileMap : rowMap.toCharArray()) {
                MapLegend mapLegend = getMapLegendFromSymbol(String.valueOf(oneTileMap));
                index = createSaveTitleMap(index, mapLegend);
            }
            index = generateMultiMapTitle(index, 1, MapLegend.DEFAULT);
        }
        index = generateMultiMapTitle(index, globalSettings.getMapSizeWidth() + 2, MapLegend.DEFAULT);
        if (globalSettings.getMapSizeWidth() * globalSettings.getMapSizeHeight() > index) {
            logger.logMessage("Map not found for index from [" + index + "] to ["
                    + globalSettings.getMapSizeWidth() * globalSettings.getMapSizeHeight() + "].", true);
            for (int i = index; i < globalSettings.getMapSizeWidth() * globalSettings.getMapSizeHeight(); i++) {
                index = createSaveTitleMap(index, MapLegend.DEFAULT);
            }
        }
    }

    private void generatePathwaysForMap() {
        mapList.values().stream()
                .parallel()
                .forEach(this::createPathwaysForTile);
    }

    private void createPathwaysForTile(Tile tile) {
        List<Tile> movementTile = tile.getMovementTile();
        int index = tile.getIndex();
        int oneRow = globalSettings.getMapSizeWidth() + 2;
        createMovementTileList(index - 1, movementTile);
        createMovementTileList(index + 1, movementTile);
        createMovementTileList(index - oneRow, movementTile);
        createMovementTileList(index - oneRow - 1, movementTile);
        createMovementTileList(index - oneRow + 1, movementTile);
        createMovementTileList(index + oneRow, movementTile);
        createMovementTileList(index + oneRow - 1, movementTile);
        createMovementTileList(index + oneRow + 1, movementTile);
    }

    private void createMovementTileList(int index, List<Tile> movementTile) {
        if (mapList.containsKey(index) && mapList.get(index).isMoving()) {
            movementTile.add(mapList.get(index));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private int generateMultiMapTitle(int index, int numbers, MapLegend mapLegend) {
        for (int i = 0; i < numbers; i++) {
            index = createSaveTitleMap(index, mapLegend);
        }
        return index;
    }

    private int createSaveTitleMap(int index, MapLegend mapLegend) {
        index++;
        String symbol = getMapSymbolFromLegend(mapLegend);
        ColorCode colorCode = globalSettings.getMapColor().get(mapLegend);
        Tile tile = new Tile(index, mapLegend, symbol, colorCode);
        mapList.put(index, tile);
        return index;
    }

    private void loadFamilyIdSettings() {
        globalSettings.loadFamilyIdSettingsList(familyIdSettingsList, this);
        loadEmptyFamilyIdSettingsToWorld();
        loadEmptyFamilyIdSettingsToTiles();
    }

    private void fillAllTilesWithCreature(int lowerNumberOfAnimals) {
        mapList.values().stream()
                .parallel()
                .forEach(tile -> createFamilyCreature(tile, lowerNumberOfAnimals));
    }

    private void createFamilyCreature(Tile tile, int lowerNumberOfAnimals) {
        if (!tile.isReproducing()) {
            return;
        }
        for (FamilyIdSettings familyIdSettings : familyIdSettingsList) {
            int max = familyIdSettings.getMaxInTitle() * lowerNumberOfAnimals;
            if (max > 0) {
                int min = 5 * max / 100;
                int maxNumberInTitle = Randomizer.randomIntFromToNotInclude(min, max);
                for (int i = 0; i < maxNumberInTitle; i++) {
                    createCreature(familyIdSettings, tile);
                }
            }
        }
    }

    public void createCreature(FamilyIdSettings familyId, Tile tile) {
        long id = generateObjectId();
        Creature creature = newObjectCreature(familyId, id);
        if (creature == null) {
            generateObjectIdDecrement();
            logger.logMessage("Can't create creature familyId [" + familyId + "].", true);
        } else {
            creature.setTile(tile);
            addCreatureToWaitList(creature);
        }
    }

    private void addAndLiveNewCreateFromWaitList() {
        creatureWaitList.forEach(this::addAndLiveCreatureToWordList);
        creatureWaitList.clear();
    }

    private void addCreatureToWaitList(Creature creature) {
        creatureWaitList.add(creature);
    }

    private void addAndLiveCreatureToWordList(Creature creature) {
        creatureList.put(creature.getId(), creature);
        creature.live();
    }

    private Creature newObjectCreature(FamilyIdSettings familyId, long id) {
        return CreatureFactory.createCreature(id, familyId.getName(), familyId.getMaxWeight(), familyId);
    }

    private long generateObjectId() {
        return indexNumberOfCreatures.incrementAndGet();
    }

    private void generateObjectIdDecrement() {
        indexNumberOfCreatures.decrementAndGet();
    }

    public Map<Integer, Tile> getMapList() {
        return mapList;
    }

    public List<FamilyIdSettings> getFamilyIdSettingsList() {
        return familyIdSettingsList;
    }


    public void incrementWorldAnimalNumbers(FamilyIdSettings familyId) {
        worldNumbersOfAnimals.get(familyId).incrementAndGet();
    }

    public void decrementWorldAnimalNumbers(FamilyIdSettings familyId) {
        worldNumbersOfAnimals.get(familyId).decrementAndGet();
    }

    private void copyNumberOfAnimalsToOriginal() {
        addAndLiveNewCreateFromWaitList();
        worldNumbersOfAnimals.forEach((key, value) -> originalNumbersOfAnimals.put(key, value.get()));
    }

    public Map<FamilyIdSettings, AtomicInteger> getWorldNumbersOfAnimals() {
        return worldNumbersOfAnimals;
    }

    public Map<FamilyIdSettings, Integer> getOriginalNumbersOfAnimals() {
        return originalNumbersOfAnimals;
    }

    public AtomicInteger getTurn() {
        return turn;
    }

    public void act() {
        prepWorldListForTurn();
        turn.incrementAndGet();
        creatureList.values().stream()
                .parallel()
                .filter(Creature::isLive)
                .forEach(Creature::act);
    }

    private void prepWorldListForTurn() {
        deleteDeadAnimals();
        addAndLiveNewCreateFromWaitList();
        clearAnimalsTileList();
        addAnimalsToTileList();
        shuffleAndCountAnimalsTileList();
    }

    private void deleteDeadAnimals() {
        creatureList.entrySet().removeIf(map -> !map.getValue().isLive());
    }

    private void clearAnimalsTileList() {
        mapList.values().stream()
                .parallel()
                .forEach(Tile::clearAnimalsList);
    }

    private void addAnimalsToTileList() {
        for (Creature creature : creatureList.values()) {
            Tile tile = creature.getTile();
            int familyIdNumber = creature.getFamilyIdSettings().getFamilyIdNumber();
            tile.addAnimalToList(familyIdNumber, creature);
        }
    }

    private void shuffleAndCountAnimalsTileList() {
        mapList.values().stream()
                .parallel()
                .forEach(Tile::shuffleAndCountAnimalsList);
    }

    public GlobalSettings getGlobalSettings() {
        return globalSettings;
    }

    public int getTotalLiveCreatures() {
        return creatureList.size();
    }
}
