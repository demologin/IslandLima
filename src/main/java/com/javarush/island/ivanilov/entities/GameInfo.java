package com.javarush.island.ivanilov.entities;

import com.javarush.island.ivanilov.game.GameField;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameInfo {
    Stats stats;
    GameField gameField;
}
