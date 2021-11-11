package com.amazing2j.amz.common;

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
}
