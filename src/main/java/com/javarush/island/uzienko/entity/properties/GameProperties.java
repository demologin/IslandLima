package com.javarush.island.uzienko.entity.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GameProperties {
    private int rows;
    private int cols;
    private int animalInCell;
    private int mainLoopPeriod;
}
