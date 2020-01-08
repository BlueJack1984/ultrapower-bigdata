package com.example.admin.dto.response;


import lombok.Data;

import java.io.Serializable;

/**
 * 调用验证码接口的返回结果
 * @author daniel
 * @date 2019-01-08
 */
@Data
public class CaptchaResult implements Serializable {

    /**
     * 产生的验证码的key，存到redis中的键key
     */
    private String captchaKey;
    /**
     * 产生的验证码值，存到redis中的值value
     */
    private String captchaValue;
}
