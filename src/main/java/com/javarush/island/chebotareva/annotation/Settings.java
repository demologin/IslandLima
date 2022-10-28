package com.javarush.island.chebotareva.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface Settings {
    double weight();
    double minWeight();
    int maxAnimalAmount();
    int speed();
    double kgFoodToBeFull();
}
