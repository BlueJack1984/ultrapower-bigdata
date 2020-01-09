package com.example.admin.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用验证码接口的返回结果
 * @author daniel
 * @date 2019-01-08
 */
@Data
public class LoginResult implements Serializable {

    private Long userId;

    private Integer targetType;

    private String account;

}
