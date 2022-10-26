package com.javarush.island.maximov.utils;

public class DivideAndConquer {
    private DivideAndConquer() {
    }

    public static int calculateNumbersIfInteger(int number) {
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 5;
                    } else {
                        return 6;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 7;
                } else {
                    return 9;
                }
            } else {
                if (number < 100000000) {
                    return 10;
                } else {
                    if (number < 1000000000) {
                        return 11;
                    } else {
                        return 12;
                    }
                }
            }
        }
    }
}
