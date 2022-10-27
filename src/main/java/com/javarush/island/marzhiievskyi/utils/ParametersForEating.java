package com.javarush.island.marzhiievskyi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.marzhiievskyi.entity.organisms.Animals;
import com.javarush.island.marzhiievskyi.utils.gettingParameters.GettingParametersOfEating;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ParametersForEating {

    private static ParametersForEating parametersForEating;

    private final GettingParametersOfEating gettingParametersOfEating;

    private ParametersForEating(){
        try {
            ObjectMapper mapper = new YAMLMapper();
            gettingParametersOfEating = mapper.readValue(new File(Constants.ORGANISM_EAT_CHANCE_FILE_PATH), GettingParametersOfEating.class);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static ParametersForEating getParametersForEating() {
        if (parametersForEating == null) {
            parametersForEating = new ParametersForEating();
        }
        return parametersForEating;
    }

    public List<GettingParametersOfEating.AnimalsEatable> getEatParameters(Animals animal) {
        String name = animal.getName().toLowerCase();
        return gettingParametersOfEating.getWhoCanEat().get(name);
    }
}
