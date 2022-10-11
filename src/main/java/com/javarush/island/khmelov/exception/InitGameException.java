package com.javarush.island.khmelov.exception;

public class InitGameException extends RuntimeException{
    public InitGameException() {
    }

    public InitGameException(String message) {
        super(message);
    }

    public InitGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitGameException(Throwable cause) {
        super(cause);
    }
}
