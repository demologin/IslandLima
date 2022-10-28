package com.javarush.island.ivanilov.exceptions;

public class IslandGameException extends RuntimeException {
    public IslandGameException() {
    }

    public IslandGameException(String message) {
        super(message);
    }

    public IslandGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IslandGameException(Throwable cause) {
        super(cause);
    }

    public IslandGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
