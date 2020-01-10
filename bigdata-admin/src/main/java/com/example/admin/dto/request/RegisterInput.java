package com.example.admin.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录信息输入
 * @author daniel
 * @date 2019-01-07
 */
@Data
public class RegisterInput implements Serializable {

    /**
     * 手机号
     */
    @NotBlank
    private String phoneNumber;
    /**
     * 输入密码
     */
    @NotBlank
    private String password;
    /**
     * 确认密码
     */
    @NotBlank
    private String confirmPassword;
    /**
     * 用户输入的验证码
     */
    @NotBlank
    private String captcha;
    /**
     * 后台返回的存入redis的验证码的key
     */
    @NotBlank
    private String captchaKey;
}
