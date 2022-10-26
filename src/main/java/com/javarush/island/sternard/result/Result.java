package com.javarush.island.sternard.result;

import lombok.Getter;

@Getter
public class Result {
    private final ResultCode resultCode;
    private String message;

    public Result(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result(ResultCode resultCode, String message) {
        this(resultCode);
        this.message = message;
    }
}