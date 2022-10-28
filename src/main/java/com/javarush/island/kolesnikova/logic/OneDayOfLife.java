package com.javarush.island.kolesnikova.logic;


import com.javarush.island.kolesnikova.actions.Eating;
import com.javarush.island.kolesnikova.actions.Reproduction;
import com.javarush.island.kolesnikova.actions.Running;


public class OneDayOfLife {

    public static void start() {
        int i = 1;
        while (true) {
            System.out.println("\n=================  День " + (i) + " =================");
            i++;
            if (Analytics.getCellsStat() > 0) {
                Reproduction.multiply();
                Running.run();
                Eating.eat();
            } else {
                break;
            }

        }
    }
}

