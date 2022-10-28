package com.javarush.island.alimin;

import com.javarush.island.alimin.entity.location.Location;
import com.javarush.island.alimin.services.LocationWorker;

public class Runner {
    public static void main(String[] args) {
        Location location = Location.getInstance();
        location.initialize();
        LocationWorker locationWorker = new LocationWorker();
        locationWorker.start();
    }
}
