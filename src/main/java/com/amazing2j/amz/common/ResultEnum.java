package com.amazing2j.amz.common;

public enum ResultEnum {
    SUCCESS(200, "success"),
    FAIL(5000, "failed");


    public int code;
    public String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
