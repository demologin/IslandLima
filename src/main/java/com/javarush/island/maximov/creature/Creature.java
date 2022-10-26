package com.javarush.island.maximov.creature;

import com.javarush.island.maximov.creature.engine.*;
import com.javarush.island.maximov.exception.ActionException;
import com.javarush.island.maximov.settings.FamilyIdSettings;
import com.javarush.island.maximov.utils.Randomizer;
import com.javarush.island.maximov.world.Tile;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
    protected final long id;
    protected final String name;
    protected final FamilyIdSettings familyIdSettings;
    protected final List<Actions> actionsTurnList = new ArrayList<>();
    protected final List<Actions> actionsAlwaysList = new ArrayList<>();
    protected double currentWeight;
    protected boolean isLive = false;
    protected Tile tile;

    protected Creature(long id, String name, FamilyIdSettings familyIdSettings, double currentWeight) {
        this.id = id;
        this.familyIdSettings = familyIdSettings;
        this.currentWeight = currentWeight;
        this.name = name + "-" + id;
        init();
    }

    private void init() {
        if (this instanceof Mortal) {
            actionsAlwaysList.add(Actions.MORTAL);
        }
        if (this instanceof Eating) {
            actionsTurnList.add(Actions.EATING);
        }
        if (this instanceof Movable) {
            actionsTurnList.add(Actions.MOVABLE);
        }
        if (this instanceof Reproducing) {
            actionsTurnList.add(Actions.REPRODUCING);
        }
    }

    public long getId() {
        return id;
    }

    public FamilyIdSettings getFamilyIdSettings() {
        return familyIdSettings;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public boolean isLive() {
        return isLive;
    }

    public void act() {
        if (!isLive) {
            return;
        }

        if (!actionsAlwaysList.isEmpty()) {
            for (Actions actions : actionsAlwaysList) {
                if (isLive()) {
                    if (actions == Actions.MORTAL) {
                        ((Mortal) this).startDie(this);
                    } else {
                        throw new ActionException("No always action for " + this.name);
                    }
                }
            }
        }

        if (!actionsTurnList.isEmpty() && isLive) {
            switch (actionsTurnList.get(Randomizer.randomIntFromToNotInclude(0, actionsTurnList.size()))) {
                case EATING -> ((Eating) this).startEat(this);
                case MOVABLE -> ((Movable) this).startMove(this);
                case REPRODUCING -> ((Reproducing) this).startReproduction(this);
                default -> throw new ActionException("No turn action for " + this.name);
            }
        }
    }

    public void die() {
        isLive = false;
        familyIdSettings.getWorld().decrementWorldAnimalNumbers(familyIdSettings);
        tile.decrementAnimalNumbers(familyIdSettings);
    }

    public void live() {
        familyIdSettings.getWorld().incrementWorldAnimalNumbers(familyIdSettings);
        tile.incrementAnimalNumbers(familyIdSettings);
        isLive = true;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }


}
