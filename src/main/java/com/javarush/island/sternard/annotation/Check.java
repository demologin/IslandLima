package com.javarush.island.sternard.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    boolean isNull() default false;

    boolean isEmpty() default false;

    int minValue() default 0;

    int maxValue() default 100;

    String message() default "Check input data from JSON files...";
}
