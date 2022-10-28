package com.javarush.island.dobrov;

import com.javarush.island.dobrov.Field.Field;

import com.javarush.island.dobrov.services.MainWorker;

public class Runner {


    public static void main(String[] args) {
        System.out.println(" Не поехала многопоточка (");
        Field field = new Field();
       MainWorker worker = new MainWorker(field);
        worker.go();

    }
}