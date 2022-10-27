package com.javarush.island.marzhiievskyi.utils.gettingParameters;

import java.util.List;
import java.util.Map;

public class GettingParametersOfEating {
    private Map<String, List<AnimalsEatable>> whoCanEat;

    public Map<String, List<AnimalsEatable>> getWhoCanEat() {
        return whoCanEat;
    }

    public void setWhoCanEat(Map<String, List<AnimalsEatable>> whoCanEat) {
        this.whoCanEat = whoCanEat;
    }

    public static class AnimalsEatable {

        private String name;

        private int chanceToEat;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getChanceToEat() {
            return chanceToEat;
        }

        public void setChanceToEat(int chanceToEat) {
            this.chanceToEat = chanceToEat;
        }

        @Override
        public String toString() {
            return name + " chance be ate = " + chanceToEat;
        }
    }

}
