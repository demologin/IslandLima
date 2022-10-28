package com.javarush.island.uzienko.utils;

import java.io.File;

public final class PathFinder {
    private PathFinder() {
    }

    public static String getRoot() {
        String root = System.getProperty("user.dir");
        return root + File.separator;
    }

    public static String getSrc() {
        return PathFinder.getRoot() + "src" + File.separator;
    }

    public static String getMain() {
        return PathFinder.getSrc() + "main" + File.separator;
    }

    public static String getResources() {
        return PathFinder.getMain() + File.separator + "resources" + File.separator;
    }
}
