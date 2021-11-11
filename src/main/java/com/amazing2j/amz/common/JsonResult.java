package com.amazing2j.amz.common;

public class JsonResult {

    private int code = 200;

    private String message = "success";

    private Object data;

    public JsonResult setCode(ResultEnum resultCode){
        this.code = resultCode.code;
        return this;
    }

    public JsonResult setMessage(String message){
        this.message = message;
        return this;
    }

    public JsonResult setData(Object data){
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
