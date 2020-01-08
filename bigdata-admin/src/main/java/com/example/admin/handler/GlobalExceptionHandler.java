package com.example.admin.handler;

import com.example.admin.dto.response.OutputResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理
 * @author daniel
 * @date 2019-12-30
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public OutputResult<Void> handle() {
        return null;
    }
}
