package com.javarush.island.ivanilov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BreedingParams {

    private int pregnancyLength;
    private int minOffsprings;
    private int maxOffsprings;

    public BreedingParams() {
    }

    @Override
    public String toString() {
        return "BreedingParameters{" +
                ", pregnancyLength=" + pregnancyLength +
                ", minOffsprings=" + minOffsprings +
                ", maxOffsprings=" + maxOffsprings +
                '}';
    }
}