package com.javarush.island.maximov.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger {
    private final String filename;
    private final DateTimeFormatter dateTimeFormatter;
    private BufferedWriter bufferedWriter;

    public Logger(String filename, String dataFormat) {
        this.filename = filename;
        dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        initialFile();
    }

    private void initialFile() {
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void saveMessage(String text) {
        try {
            bufferedWriter.write(text);
        } catch (IOException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public void logMessage(String text, boolean isError) {
        text = addTime(text, isError);
        printMessage(text, isError);
        saveMessage(text);
    }

    public void displayMessageStringBuilder(StringBuilder text) {
        System.out.println(text);
    }

    private String addTime(String text, boolean isError) {
        return (isError ? "[ERROR] " : "")
                + String.format("[%s]: %s%n", dateTimeFormatter.format(LocalDateTime.now()), text);
    }

    private void printMessage(String text, boolean isError) {
        if (isError) {
            System.err.println(text);
        } else {
            System.out.print(text);
        }
    }
}
