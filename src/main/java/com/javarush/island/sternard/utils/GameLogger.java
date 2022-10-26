package com.javarush.island.sternard.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameLogger {
    public static Logger getLog() {
        return LogManager.getLogger();
    }
}
