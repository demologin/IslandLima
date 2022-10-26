package com.javarush.island.maximov.utils;

public class Color {

    public static final String UNICODE_COLOR = "\u001B[38;5;";

    public String selectColor(ColorCode colorCode) {
        return switch (colorCode) {
            case FONT_BLACK -> UNICODE_COLOR + 0 + "m";
            case FONT_GRAY -> UNICODE_COLOR + 240 + "m";
            case FONT_BLUE -> UNICODE_COLOR + 27 + "m";
            case FONT_GREEN -> UNICODE_COLOR + 29 + "m";
            case FONT_DARK_GREEN -> UNICODE_COLOR + 65 + "m";
            case FONT_DARK_RED -> UNICODE_COLOR + 203 + "m";
            case FONT_DARK_BLUE -> UNICODE_COLOR + 20 + "m";
            case FONT_DARK_BROWN -> UNICODE_COLOR + 16 + "m";
            case FONT_VINOUS -> UNICODE_COLOR + 134 + "m";
            case FONT_AMBER -> UNICODE_COLOR + 39 + "m";
            case BACK_WHITE -> "\u001B[48;5;" + 15 + "m";
            case BOLD -> "\u001B[1m";
            case RESET -> "\u001B[m";
        };
    }
}
