package com.amazing2j.amz.utils;

import com.amazing2j.amz.constant.enums.ResultEnum;

public class JsonResult {

    private int code = 200;

    private String message = "success";

    private Object data;

    public JsonResult setCode(ResultEnum resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public JsonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
