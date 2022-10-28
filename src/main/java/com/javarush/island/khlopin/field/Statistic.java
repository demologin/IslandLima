package com.javarush.island.khlopin.field;

import com.javarush.island.khlopin.exception.IslandApplicationException;
import com.javarush.island.khlopin.settings.Properties;
import com.javarush.island.khlopin.units.Unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {

    public Map<String, Integer> printState()  {
        Map<String, Integer> countMap = new HashMap<>();
        for (Cell[] cells : GameField.field) {
            countState(countMap, cells);
        }
        return countMap;
    }

    public static void countState(Map<String, Integer> countMap, Cell[] cells) {
        for (Cell cell : cells) {
            Map<String, Integer> currentMap = new HashMap<>();
            for (Map.Entry<String, List<Unit>> stringListEntry : cell.sets.entrySet()) {
                if (stringListEntry.getValue().size() == 0) {
                    continue;
                }
                Method getProperties;
                try {
                    getProperties = stringListEntry.getValue().get(0).getClass().getMethod("getProperties");
                } catch (NoSuchMethodException e) {
                    throw new IslandApplicationException(e);
                }
                Properties invoke;
                try {
                    invoke = (Properties) getProperties.invoke(stringListEntry.getValue().get(0));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new IslandApplicationException(e);
                }
                currentMap.put(invoke.icon, stringListEntry.getValue().size() + currentMap.getOrDefault(stringListEntry.getKey(), 0));
            }
            currentMap.forEach((k, v) -> countMap.merge(k, v, Integer::sum));
        }
    }

}
