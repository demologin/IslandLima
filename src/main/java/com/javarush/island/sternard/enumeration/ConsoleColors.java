package com.javarush.island.sternard.enumeration;

@SuppressWarnings("unused")
public enum ConsoleColors {
    // High Intensity Colors
    WHITE("\033[0;97m"),
    BLACK("\033[0;90m"),
    BLUE("\u001B[1;94m"),
    CYAN("\u001B[0;96m"),
    GREEN("\u001B[0;92m"),
    RED("\033[0;91m"),
    YELLOW("\033[0;93m"),
    PURPLE("\033[0;95m"),
    RESET("\033[0m");

    private final String ansiColor;

    ConsoleColors(String ansiColor) {
        this.ansiColor = ansiColor;
    }

    public String getAnsiColor() {
        return ansiColor;
    }
}
