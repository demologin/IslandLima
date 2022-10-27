package com.javarush.island.shubchynskyi.services;

import com.javarush.island.shubchynskyi.entity.animals.Organism;

public class OrganismTask {

    private final Organism organism;

    public OrganismTask(Organism organism) {
        this.organism = organism;
    }

    public void startTask() {
        if (organism.isAlive()) {
            organism.startLife();
        }
    }

}
