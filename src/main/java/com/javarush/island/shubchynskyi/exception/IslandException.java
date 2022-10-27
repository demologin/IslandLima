package com.javarush.island.shubchynskyi.exception;

public class IslandException extends RuntimeException {
    public IslandException() {
    }

    public IslandException(String message) {
        super(message);
    }

    public IslandException(String message, Throwable cause) {
        System.out.println(message + cause);
    }

    public IslandException(Throwable cause) {
        super(cause);
    }
}
