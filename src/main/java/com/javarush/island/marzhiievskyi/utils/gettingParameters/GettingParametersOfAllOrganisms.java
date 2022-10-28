package com.javarush.island.marzhiievskyi.utils.gettingParameters;

import java.util.List;

public class GettingParametersOfAllOrganisms {
    private List<OrganismParameters> organismParametersList;

    public List<OrganismParameters> getOrganismParametersList() {
        return organismParametersList;
    }

    public void setOrganismParametersList(List<OrganismParameters> organismParametersList) {
        this.organismParametersList = organismParametersList;
    }

    @Override
    public String toString() {
        return "All organism params: " + organismParametersList;
    }

    public static class OrganismParameters {
        private String name;
        private String icon;
        private double weight;
        private int maxCountOnCell;
        private int maxSpeedCells;
        private double needFood;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getMaxCountOnCell() {
            return maxCountOnCell;
        }

        public void setMaxCountOnCell(int maxCountOnCell) {
            this.maxCountOnCell = maxCountOnCell;
        }

        public int getMaxSpeedCells() {
            return maxSpeedCells;
        }

        public void setMaxSpeedCells(int maxSpeedCells) {
            this.maxSpeedCells = maxSpeedCells;
        }

        public double getNeedFood() {
            return needFood;
        }

        public void setNeedFood(double needFood) {
            this.needFood = needFood;
        }

        @Override
        public String toString() {
            return "Organism: " + name
                    + " " + icon;
        }
    }
}
