package com.example.core.utils;

import java.time.LocalDateTime;

/**
 * @Author liuliying@ultrapower.com.cn
 * @Description 验证码实体类
 * @Date 11:05 2020/1/6
 **/
public class CaptchaItem {
    private String phoneNumber;
    private String code;
    private LocalDateTime expireTime;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public LocalDateTime getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}