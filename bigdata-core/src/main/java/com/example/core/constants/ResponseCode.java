package com.example.core.constants;

/**
 * 响应码枚举
 * @author daniel
 * @date 2019-12-23
 */
public enum ResponseCode {
    SUCCESS(200, ""), ERROR(201, ""),
    ILLEGAL(202, "");
    private Integer code;

    private String message;

    private ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String  getMessage() {
        return message;
    }
}
