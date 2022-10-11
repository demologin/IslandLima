package com.javarush.island.khmelov.util;

import com.javarush.island.khmelov.abstraction.annotations.Setting;
import com.javarush.island.khmelov.exception.InitGameException;
import com.javarush.island.khmelov.entity.organizms.Limit;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.util.Probably;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityFactoryData {

    private EntityFactoryData() {
    }

    public static Organism[] createPrototypes(Class<?>[] TYPES) {
        Organism[] organisms = new Organism[TYPES.length];
        int index = 0;
        for (Class<?> type : TYPES) {
            if (type.isAnnotationPresent(Setting.class)) {
                Setting setting = type.getAnnotation(Setting.class);
                String name = setting.name();
                String icon = setting.icon();
                double maxWeight = setting.maxWeight();
                double weight = Probably.random(maxWeight / 2, maxWeight);
                Limit limit = new Limit(
                        setting.maxWeight(),
                        setting.maxCount(),
                        setting.maxSpeed(),
                        setting.maxFood()
                );

                organisms[index++] = generatePrototype(type, name, icon, weight, limit);
            }
        }
        return organisms;
    }

    private static Organism generatePrototype(Class<?> type, String name, String icon, double weight, Limit limit) {
        try {
            Constructor<?> constructor = type.getConstructor(String.class, String.class, double.class, Limit.class);
            return (Organism) constructor.newInstance(name, icon, weight, limit);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InitGameException("not found Entity constructor", e);
        }
    }
}
