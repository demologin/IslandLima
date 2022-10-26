package com.javarush.island.ivanilov.utils;

import com.javarush.island.ivanilov.exceptions.IslandGameException;
import com.javarush.island.ivanilov.game.Settings;

public class Sleeper {

    public static void sleep() {
        try{
            Thread.sleep(Settings.settings.ITERATION_SPEED_IN_MILLIS);
        } catch (Exception e) {
            throw new IslandGameException("Thread has been interrupted");
        }
    }

    public static void sleep(long milliseconds) {
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            throw new IslandGameException("Thread has been interrupted");
        }
    }
}
