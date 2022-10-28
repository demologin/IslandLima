package com.javarush.island.osypenko.exception;

@SuppressWarnings("unused")
public class AnimalException extends RuntimeException{
    public AnimalException() {
        super();
    }

    public AnimalException(String message) {
        super(message);
    }

    public AnimalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnimalException(Throwable cause) {
        super(cause);
    }
}
