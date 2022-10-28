package com.javarush.island.bogdan.island;


public class Island {
    private final Location[][] locations;
    private int day;

    public Island(int cols, int rows) {
        this.locations = new Location[cols][rows];
        this.day = 0;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public int getDay() {
        return day;
    }

    public void plusDay() {
        ++this.day;
    }

    public void showStatistic(int day) {
        day++;
        System.out.println("\uD83C\uDFDD".repeat(18) + " day " + day + " " + "\uD83C\uDFDD".repeat(19));
        for (int col = 0; col < locations.length; col++) {
            for (int row = 0; row < locations[col].length; row++) {
                System.out.print("[" + col + "," + row + "] ");
                locations[col][row].toString();
                System.out.println();
            }

        }
    }
}
