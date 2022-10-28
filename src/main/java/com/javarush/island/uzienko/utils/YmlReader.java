package com.javarush.island.uzienko.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.uzienko.entity.properties.GameProperties;
import com.javarush.island.uzienko.exceptions.IslandException;
import com.javarush.island.uzienko.entity.properties.ResidentProperties;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class YmlReader {
    private YmlReader() {
    }

    public static List<ResidentProperties> getResidents(String fileName) {
        ObjectMapper mapper = new YAMLMapper();
        File file = new File(PathFinder.getResources() + fileName);
        try {
            return mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IslandException(e);
        }
    }

    public static GameProperties getGameProperties(String fileName) {
        ObjectMapper mapper = new YAMLMapper();
        File file = new File(PathFinder.getResources() + fileName);
        try {
            return mapper.readValue(file, GameProperties.class);
        } catch (IOException e) {
            throw new IslandException(e);
        }
    }
}
