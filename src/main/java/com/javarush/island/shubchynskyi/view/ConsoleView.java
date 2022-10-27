package com.javarush.island.shubchynskyi.view;

import com.javarush.island.shubchynskyi.entity.animals.Animal;
import com.javarush.island.shubchynskyi.entity.gamefield.Cell;
import com.javarush.island.shubchynskyi.entity.gamefield.GameField;
import com.javarush.island.shubchynskyi.entity.plants.Plant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.javarush.island.shubchynskyi.entity.EntityFactory.getAnimalPrototypes;
import static com.javarush.island.shubchynskyi.entity.EntityFactory.getPlantPrototypes;
import static com.javarush.island.shubchynskyi.settings.Constants.GAME_OVER_MESSAGE;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.caterpillarAvatar;
import static com.javarush.island.shubchynskyi.settings.EntitySettings.mouseAvatar;
import static com.javarush.island.shubchynskyi.settings.GameSettings.*;
import static com.javarush.island.shubchynskyi.view.Colors.*;

public class ConsoleView implements View {

    private final GameField gameField;
    private final Map<String, Integer> statisticMap = new HashMap<>();
    private int updateCount = 0;
    private boolean isGameStop = false;

    public ConsoleView(GameField gameField) {
        this.gameField = gameField;
    }

    public boolean isGameStop() {
        return isGameStop;
    }

    @Override
    public void getGameStopMessage() {
        System.out.println(COLOR_YELLOW + GAME_OVER_MESSAGE + COLOR_RESET);
    }

    @Override
    public void showMap() {
        for (int y = 0; y < gameField.getHeight(); y++) {
            for (int x = 0; x < gameField.getWidth(); x++) {
                Cell currentCell = gameField.getGameField()[x][y];
                String maxAvatar = "*"; // avatar for empty cell
                int maxSize = 0;
                maxAvatar = getMaxAvatarForCell(currentCell, maxAvatar, maxSize);
                System.out.print(maxAvatar);
                if (x == gameField.getWidth() - 1) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * mouse and caterpillar are excluded from drawing
     */
    private String getMaxAvatarForCell(Cell currentCell, String maxAvatar, int maxSize) {
        for (var entry : currentCell.getAnimalsInCell().entrySet()) {
            if (!entry.getKey().equals(mouseAvatar) && !entry.getKey().equals(caterpillarAvatar)) {
                if (maxSize < entry.getValue().size()) {
                    maxSize = entry.getValue().size();
                    maxAvatar = entry.getKey();
                }
            }
        }

        if (maxSize == 0) {
            for (var entry : currentCell.getPlantsInCell().entrySet()) {
                if (maxSize < entry.getValue().size()) {
                    maxSize = entry.getValue().size();
                    maxAvatar = entry.getKey();
                }
            }
        }
        return maxAvatar;
    }

    @Override
    public void showStatistic() {
        System.out.println(getStatistic());
    }

    public void setGameStop(boolean stop) {
        isGameStop = stop;
    }

    public String getStatistic() {
        StringBuilder result = new StringBuilder();
        boolean isGameStop = true;

        statisticMapInitAndClear();
        collectStatistic();

        result.append(getDays());
        result.append(COLOR_GREEN);

        int currentView = 0;
        int maxViewInString = 9;
        for (var var : statisticMap.entrySet()) {
            if (var.getValue() > 0) {
                result.append(var.getKey()).append(" = ").append(var.getValue() * CHEAT_LEVEL).append("; ");
                if (++currentView == maxViewInString) result.append("\n");
                if (!Arrays.asList(GAME_STOP_CONDITIONS).contains(var.getKey())) {
                    isGameStop = false;
                }
            }
        }
        result.append(COLOR_RESET);

        setGameStop(isGameStop);
        return result.toString();
    }

    private String getDays() {
        return COLOR_BLUE + "Day " + ++updateCount + " of " + MAX_TICKS + "\n";
    }

    private void collectStatistic() {
        int oldValue;
        int newValue;
        String type;

        for (Cell[] cells : gameField.getGameField()) {
            for (Cell cell : cells) {
                for (var var : cell.getAnimalsInCell().entrySet()) {
                    type = var.getKey();
                    newValue = var.getValue().size();
                    oldValue = statisticMap.get(type);
                    statisticMap.put(type, oldValue + newValue);
                }
                for (var var : cell.getPlantsInCell().entrySet()) {
                    type = var.getKey();
                    newValue = var.getValue().size();
                    oldValue = statisticMap.get(type);
                    statisticMap.put(type, oldValue + newValue);
                }
            }
        }
    }

    private void statisticMapInitAndClear() {
        for (Animal prototype : getAnimalPrototypes()) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
        for (Plant prototype : getPlantPrototypes()) {
            statisticMap.put(prototype.getAvatar(), 0);
        }
    }
}