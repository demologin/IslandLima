package com.javarush.island.khmelov.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.khmelov.entity.organizms.Organism;
import com.javarush.island.khmelov.entity.organizms.animals.herbivores.Horse;
import com.javarush.island.khmelov.entity.organizms.animals.predators.Wolf;
import com.javarush.island.khmelov.entity.organizms.plants.Plant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    public static final String SETTING_YAML = "setting.yaml";
    private static final Class<?>[] TYPES = {Plant.class, Wolf.class, Horse.class};
    public static final Organism[] PROTOTYPES = EntityScanner.createPrototypes(TYPES);

    //======================== SAFE_THREAD_SINGLETON =============================
    private static volatile Setting SETTING;

    public static Setting get() {
        Setting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (Objects.isNull(setting = SETTING)) {
                    setting = SETTING = new Setting();
                }
            }
        }
        return setting;
    }
    //======================== /SAFE_THREAD_SINGLETON =============================


    //=============================== DATA ========================================

    private int period;
    private int rows;
    private int cols;
    private int consoleCellWith;
    @Getter(AccessLevel.PROTECTED)
    private Map<String, Map<String, Integer>> foodMap = new LinkedHashMap<>();

    public Map<String, Integer> getFoodMap(String keyName) {
        this.foodMap.putIfAbsent(keyName, new LinkedHashMap<>());
        return foodMap.get(keyName);
    }
    //=============================== /DATA ========================================

    //================================ INIT ========================================

    private Setting() {
        loadFromDefault();
        updateFromYaml();
    }

    private void loadFromDefault() {
        period = Default.PERIOD;
        rows = Default.ROWS;
        cols = Default.COLS;
        consoleCellWith = Default.CONSOLE_CELL_WITH;
        for (int i = 0, n = Default.names.length; i < n; i++) {
            String key = Default.names[i];
            this.foodMap.putIfAbsent(key, new LinkedHashMap<>());
            for (int j = 0; j < n; j++) {
                int ratio = Default.setProbablyTable[i][j];
                if (ratio > 0) {
                    this.foodMap.get(key).put(Default.names[j], ratio);
                }
            }
        }
    }

    @SneakyThrows
    private void updateFromYaml() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectReader readerForUpdating = mapper.readerForUpdating(this);
        URL resource = Setting.class.getClassLoader().getResource(SETTING_YAML);
        if (Objects.nonNull(resource)) {
            readerForUpdating.readValue(resource.openStream());
        }
    }
    //=============================== /INIT ========================================

    //=============================== FOR DEBUG ONLY ===============================
    @Override
    public String toString() {
        ObjectMapper yaml = new ObjectMapper(new YAMLFactory());
        yaml.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return yaml.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //=============================== /FOR DEBUG ONLY===============================

}
