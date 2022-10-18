package com.javarush.island.khmelov.entity;

import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.repository.Factory;
import com.javarush.island.khmelov.view.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Game {

    private final GameMap gameMap;
    private final Factory entityFactory;
    private final View view;

}
