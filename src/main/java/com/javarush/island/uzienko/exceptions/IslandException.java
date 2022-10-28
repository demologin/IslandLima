package com.javarush.island.uzienko.exceptions;

public class IslandException extends RuntimeException{
    public IslandException() {
        super();
    }

    public IslandException(String message) {
        super(message);
    }

    public IslandException(String message, Throwable cause) {
        super(message, cause);
    }

    public IslandException(Throwable cause) {
        super(cause);
    }
}
