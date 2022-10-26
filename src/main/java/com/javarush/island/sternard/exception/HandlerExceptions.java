package com.javarush.island.sternard.exception;

import com.javarush.island.sternard.controller.Controller;
import com.javarush.island.sternard.settings.Settings;

public class HandlerExceptions extends RuntimeException {
    public HandlerExceptions(String message) {
        System.err.println(message);
        Controller.executorService.shutdown();  // чтобы хоть как-то остановить программу
    }

    public HandlerExceptions(String message, StackTraceElement[] stackTrace) {
        showStackTraceOrNo(message, stackTrace);
    }

    private void showStackTraceOrNo(String message, StackTraceElement[] stackTrace) {
        boolean exceptionShowStackTrace = Settings.get().isExceptionShowStackTrace();
        System.err.println(message);
        if (!exceptionShowStackTrace) {
            System.exit(1);
        } else {
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement);
            }
            System.exit(1);
        }
    }

}