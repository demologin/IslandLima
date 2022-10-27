package com.javarush.island.dobrov.utils;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Animal_Properties {

    double weight();
    int maxAreaPopulation();
    int speed() default 0;
    double hunger() default 0;
    //String icon();

}
