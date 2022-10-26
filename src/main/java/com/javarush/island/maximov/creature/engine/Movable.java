package com.javarush.island.maximov.creature.engine;

import com.javarush.island.maximov.creature.Creature;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.settings.MapLegend;
import com.javarush.island.maximov.utils.Randomizer;
import com.javarush.island.maximov.world.Tile;

public interface Movable {
    default void startMove(Creature creature) {
        Tile currentTile = creature.getTile();
        if (isNewTileForMove(currentTile)) {
            FamilyIdSettings familyIdSettings = creature.getFamilyIdSettings();
            int totalPoints = getPointForMove(familyIdSettings);
            moveWithPoint(creature, familyIdSettings, currentTile, totalPoints);
            if (isTileRiver(currentTile)) {
                creature.die();
            }
        }
    }

    default void moveWithPoint(Creature creature, FamilyIdSettings familyIdSettings, Tile currentTile,
                               int totalPoints) {
        Tile newTile = getTileForMove(currentTile);
        int pointForMove = calcPointForMove(newTile);
        if (totalPoints >= pointForMove) {
            moveToNewTile(creature, familyIdSettings, currentTile, newTile);
            moveWithPoint(creature, familyIdSettings, newTile, totalPoints - pointForMove);
        }
    }

    default void moveToNewTile(Creature creature, FamilyIdSettings familyIdSettings, Tile currentTile, Tile newTile) {
        currentTile.getNumbersOfAnimals().get(familyIdSettings).decrementAndGet();
        newTile.getNumbersOfAnimals().get(familyIdSettings).incrementAndGet();
        creature.setTile(newTile);
    }

    default boolean isTileRiver(Tile tile) {
        return tile.getMapLegend().equals(MapLegend.RIVER);
    }

    default int getPointForMove(FamilyIdSettings familyIdSettings) {
        return familyIdSettings.getSpeed();
    }


    default int calcPointForMove(Tile tileForMove) {
        return switch (tileForMove.getMapLegend()) {
            case DEFAULT, MOUNTAIN -> 100;
            case RIVER -> 2;
            case FIELD -> 1;
        };
    }

    default boolean isNewTileForMove(Tile currentTile) {
        return !currentTile.getMovementTile().isEmpty();
    }

    default Tile getTileForMove(Tile currentTile) {
        int random = Randomizer.randomIntFromToNotInclude(0, currentTile.getMovementTile().size());
        return currentTile.getMovementTile().get(random);
    }
}
