package com.javarush.island.sternard;

import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.services.CellService;
import com.javarush.island.sternard.services.PlantGrowService;
import com.javarush.island.sternard.services.ShowStatisticsService;
import com.javarush.island.sternard.settings.Settings;
import com.javarush.island.sternard.utils.GameLogger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.javarush.island.sternard.constant.lang.English.THE_GAME_WAS_EMERGENCY_STOPPED;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Runner {

    public static void main(String[] args) {

        int plantGrowPeriod = Settings.get().getPlantGrowPeriod();
        int showStatisticsPeriod = Settings.get().getShowStatisticsPeriod();
        int cellServicePeriod = Settings.get().getCellServicePeriod();

        Controller controller = new Controller();
        controller.initGame();

        Runnable plantGrowService = new PlantGrowService(controller).createPlantGrowService();
        Runnable statisticsService = new ShowStatisticsService(controller).showStatisticsServiceStart();
        Runnable cellServiceStart = new CellService(controller).cellServiceStart();

        controller.startService(statisticsService, 10L, showStatisticsPeriod);
        controller.startService(plantGrowService, 20L, plantGrowPeriod);
        controller.startService(cellServiceStart, 30L, cellServicePeriod);

        // if game emergency stopped. Сan be deleted
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            boolean endCellService = controller.isEndCellService(controller.getDAY_NUMBER().get());
            if (!endCellService)
                GameLogger.getLog().warn(THE_GAME_WAS_EMERGENCY_STOPPED);
        }));
    }
}
