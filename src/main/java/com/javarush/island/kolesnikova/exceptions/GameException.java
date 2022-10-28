package com.javarush.island.kolesnikova.exceptions;

public class GameException extends RuntimeException{

    public GameException() {
        super();
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(Throwable cause) {
        super(cause);
    }
}
