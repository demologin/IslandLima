package com.javarush.island.shubchynskyi.utils;

import com.javarush.island.shubchynskyi.settings.EntitySettings;

import static com.javarush.island.shubchynskyi.settings.Constants.MAX_PER_CELL;
import static com.javarush.island.shubchynskyi.settings.GameSettings.CHEAT_LEVEL;

public class FieldCreator {
    private FieldCreator() {
    }

    public static Object getField(Object object, String fieldName) {
        try {
            if (!fieldName.equals(MAX_PER_CELL)) {
                return EntitySettings.class.getField(object.getClass().getSimpleName().toLowerCase() + fieldName)
                        .get(EntitySettings.class);
            } else {
                int result = (int) EntitySettings.class.getField(object.getClass().getSimpleName().toLowerCase() + fieldName)
                        .get(EntitySettings.class);
                return Math.max(result / CHEAT_LEVEL, 1);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
