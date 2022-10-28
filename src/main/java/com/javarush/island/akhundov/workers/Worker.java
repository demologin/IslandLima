package com.javarush.island.akhundov.workers;

import com.javarush.island.akhundov.services.AbstractService;

public class Worker implements Runnable{
    AbstractService service;

    public Worker(AbstractService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.runService();
    }
}
