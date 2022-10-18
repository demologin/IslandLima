package com.javarush.island.khmelov.entity.organizms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Limit {
    private final double maxWeight;
    private final int maxCount;
    private final int maxSpeed;
    private final int maxFood;
}
