package com.example.admin.dto.response;

import com.example.core.constants.ResponseCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OutputResult<T> {

    private Integer code = 200;

    private String message = "SUCCESS";

    private T data;

    //无参数构造器，默认成功但不需要返回数据的情况
    public OutputResult() {

    }
    //有参数构造器，主要用于失败情况
    public OutputResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    //有参数构造器，主要用于失败情况，参数匹配ResponseCode
    public OutputResult(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
    //有参数构造器,主要用于成功返回数据的情况
    public OutputResult(T data) {
        this.data = data;
    }
}
