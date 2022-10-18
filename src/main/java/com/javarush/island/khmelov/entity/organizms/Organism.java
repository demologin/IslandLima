package com.javarush.island.khmelov.entity.organizms;

import com.javarush.island.khmelov.abstraction.entity.Reproducible;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.util.Probably;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode(of = "id")
public abstract class Organism implements Reproducible, Cloneable {

    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    public Organism(String name, String icon, Limit limit) {
        this.name = name;
        this.icon = icon;
        this.limit = limit;
        weight = Probably.random(limit.getMaxWeight() / 2, limit.getMaxWeight());
    }

    private long id = idCounter.incrementAndGet();
    private final String type = this.getClass().getSimpleName();
    private final String name;
    private final String icon;

    //приготовим букву заранее
    private transient final String letter = getClass().getSimpleName().substring(0, 1);
    @Setter
    private double weight;
    private final Limit limit;

    @Override
    public String toString() {
        return icon;
    }

    @Override
    protected Organism clone() throws CloneNotSupportedException {
        //visible in inherits (cast to Organism)
        Organism clone = (Organism) super.clone();
        clone.id = idCounter.incrementAndGet();
        clone.weight = Probably.random(limit.getMaxWeight() / 2, limit.getMaxWeight());
        return clone;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Organism> T clone(T original) {
        //for clients (cast to original Type)
        try {
            return (T) original.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }

    }


    protected boolean safeDie(Cell target) {
        target.getLock().lock();
        try {
            return target.getResidents().get(type).remove(this);
        } finally {
            target.getLock().unlock();
        }
    }

    protected boolean safeChangeWeight(Cell currentCell, int percent) {
        currentCell.getLock().lock();
        try {
            double maxWeight = limit.getMaxWeight();
            weight += maxWeight * percent / 100;
            weight = Math.max(0, weight);
            weight = Math.min(weight, maxWeight);
            return currentCell.getResidents().get(type).contains(this);
        } finally {
            currentCell.getLock().unlock();
        }
    }


    protected boolean safeMove(Cell source, Cell destination) {
        if (safeAddTo(destination)) { //if was added
            if (safePollFrom(source)) { //and after was extract
                return true; //ok
            } else {
                safePollFrom(destination); //die or eaten
            }
        }
        return false;
    }

    protected boolean safeAddTo(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Organism> set = cell.getResidents().get(getType());
            int maxCount = getLimit().getMaxCount();
            int size = set.size();
            return size < maxCount && set.add(this);
        } finally {
            cell.getLock().unlock();
        }
    }

    protected boolean safePollFrom(Cell cell) {
        cell.getLock().lock();
        try {
            return cell.getResidents().get(getType()).remove(this);
        } finally {
            cell.getLock().unlock();
        }
    }

    protected boolean safeFindFood(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            double needFood = getNeedFood();
            if (!(needFood <= 0)) {
                Setting setting = Setting.get();
                var foodMap = setting
                        .getFoodMap(getType())
                        .entrySet();
                var iterator = foodMap.iterator();
                while (needFood > 0 && iterator.hasNext()) {
                    Map.Entry<String, Integer> entry = iterator.next();
                    String keyFood = entry.getKey();
                    Integer probably = entry.getValue();
                    var foods = currentCell.getResidents().get(keyFood);
                    if (foods.size() > 0 && probably > Probably.random(0, 100)) {
                        for (Iterator<Organism> organismIterator = foods.iterator(); organismIterator.hasNext(); ) {
                            Organism o = organismIterator.next();
                            double foodWeight = o.getWeight();
                            double delta = Math.min(foodWeight, needFood);
                            double weight = getWeight();
                            setWeight(weight + delta);
                            o.setWeight(foodWeight - delta);
                            if (o.getWeight() <= 0) {
                                organismIterator.remove();
                            }
                            needFood -= delta;
                            if (needFood <= 0) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                return false;
            }
        } finally {
            currentCell.getLock().unlock();
        }
        return false;
    }

    private double getNeedFood() {
        return Math.min(
                getLimit().getMaxFood(),
                getLimit().getMaxWeight() - getWeight());
    }

}
