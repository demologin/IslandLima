package com.javarush.island.khlopin.exception;

public class IslandApplicationException extends RuntimeException {
    public IslandApplicationException(ReflectiveOperationException ignoredE) {

    }

    public IslandApplicationException() {
    }
}
