package com.javarush.island.akhundov;

import com.javarush.island.akhundov.field.GameField;
import com.javarush.island.akhundov.workers.GameWorker;


public class Runner {
   public static void main(String[] args) {
      GameField gameField = new GameField(100, 20);
      GameWorker gameWorker = new GameWorker(gameField);
      gameWorker.start();


      //Однопоточный запуск
     /*
      InitializationService initializationService = new InitializationService(gameField);
      Preferences preferences = new Preferences();
      EatingService eatingService = new EatingService(gameField, preferences);
      MoveService moveService = new MoveService(gameField);
      DyingService dyingService = new DyingService(gameField);
      Printer printer = new Printer(gameField,preferences);

      initializationService.runService();
      while(true){
         eatingService.runService();
         moveService.runService();
         dyingService.runService();
         boolean x = printer.printMap();

         if(x)
            break;
      }
*/
   }
}
