package com.amazing2j.amz.amz_client.controller.welcome.advice;

import com.amazing2j.amz.amz_server.constant.enums.ResultEnum;
import com.amazing2j.amz.amz_server.exception.SQLException;
import com.amazing2j.amz.amz_server.utils.JsonResponse;
import com.amazing2j.amz.amz_server.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public JsonResult handler(Exception e) {
        if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            return JsonResponse.getFailResult(sqlException.getCode(), sqlException.getMessage());
        } else {
            logger.error("[系统异常]: 错误是: ", e);
            return JsonResponse.getErrorResult(ResultEnum.ERROR.code, e.getMessage());
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public JsonResult exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是: ", e);
        return JsonResponse.getErrorResult(ResultEnum.ERROR.code, e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        logger.error("参数校验异常: ", e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return JsonResponse.getErrorResult(ResultEnum.ERROR.code, e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public JsonResult exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        logger.error("参数校验异常-json转换异常: ", e);
        return JsonResponse.getErrorResult(ResultEnum.ERROR.code, e.getMessage());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Exception e) {
        return JsonResponse.getErrorResult(ResultEnum.ERROR.code, e.getMessage());
    }

}
