package com.javarush.island.kolesnikova.controller;


import com.javarush.island.kolesnikova.logic.OneDayOfLife;
import com.javarush.island.kolesnikova.logic.WordCreate;

public class MainController {


    public static void startApp() {
        WordCreate.create();
        OneDayOfLife.start();
    }







}
