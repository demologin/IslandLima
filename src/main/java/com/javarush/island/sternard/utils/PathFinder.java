package com.javarush.island.sternard.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PathFinder {
    public static String convertPathForAllOS(String path) {
        char s = File.separator.charAt(0);
        return System.getProperty("user.dir") + File.separator + path.replace('/', s);
    }
}