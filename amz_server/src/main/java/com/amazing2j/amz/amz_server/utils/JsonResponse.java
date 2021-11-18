package com.amazing2j.amz.amz_server.utils;

import com.amazing2j.amz.amz_server.constant.enums.ResultEnum;

public class JsonResponse {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    // 只返回状态
    public static JsonResult getSuccessResult() {
        return new JsonResult()
                .setCode(ResultEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    // 成功返回数据
    public static JsonResult getSuccessResult(Object data) {
        return new JsonResult()
                .setCode(ResultEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    // 失败
    public static JsonResult getFailResult(String message) {
        return new JsonResult()
                .setCode(ResultEnum.FAIL)
                .setMessage(message);
    }

    public static JsonResult getFailResult(int code, String message) {
        return new JsonResult()
                .setCode(code)
                .setMessage(message);
    }

    // 错误
    public static JsonResult getErrorResult(String message) {
        return new JsonResult().setCode(ResultEnum.ERROR).setMessage(message);
    }

    public static JsonResult getErrorResult(int code, String message) {
        return new JsonResult().setCode(code).setMessage(message);
    }
}
