package com.javarush.island.sternard.utils;

import com.javarush.island.sternard.annotation.Check;
import com.javarush.island.sternard.exception.HandlerExceptions;
import com.javarush.island.sternard.result.Result;
import com.javarush.island.sternard.result.ResultCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;

import static com.javarush.island.sternard.constant.lang.English.ACCESS_FIELD_ERROR;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckInputData {
    public static Result check(Object object) {
        Class<?> objectClass = object.getClass();
        for (Field declaredField : objectClass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Check.class)) {
                declaredField.setAccessible(true);
                Object fieldValue;
                try {
                    fieldValue = declaredField.get(object);
                } catch (IllegalAccessException e) {
                    GameLogger.getLog().error(e.getMessage(), e);
                    throw new HandlerExceptions(ACCESS_FIELD_ERROR, e.getStackTrace());
                }
                Check annotationValue = declaredField.getAnnotation(Check.class);

                if ((checkStrings(declaredField, fieldValue, annotationValue)) ||
                        (checkDigits(fieldValue, annotationValue)) ||
                        (checkMaps(fieldValue)) ||
                        (checkStringArrays(fieldValue))) {

                    String errorMessage = MessageFormat.format("{0} | Field: {1} | value: {2}",
                            declaredField.getAnnotation(Check.class).message(), declaredField.getName(), fieldValue);

                    return new Result(ResultCode.ERROR, errorMessage);
                }
            }
        }
        return new Result(ResultCode.OK);
    }

    private static boolean checkDigits(Object fieldValue, Check annotationValue) {
        if (fieldValue instanceof Number number)
            return number.intValue() < annotationValue.minValue() || number.intValue() > annotationValue.maxValue();
        return false;
    }

    private static boolean checkMaps(Object fieldValue) {
        if (fieldValue instanceof Map<?, ?> map)
            return map.size() == 0;
        return false;
    }

    private static boolean checkStringArrays(Object fieldValue) {
        if (fieldValue instanceof String[] array)
            return array.length == 0;
        return false;
    }

    private static boolean checkStrings(Field declaredField, Object fieldValue, Check annotationValue) {
        if (!annotationValue.isNull() && fieldValue == null)
            return false;

        if (fieldValue instanceof String val) {
            if (!annotationValue.isEmpty() && val.isEmpty())
                return true;
            // valid:
            // path/to/file.json
            // file.json
            return (declaredField.getName().equals("pathToOrganismsProperty")) &&
                    (!val.matches("^(\\w+\\/)*(\\w+\\.json)$"));
        }
        return false;
    }

}
