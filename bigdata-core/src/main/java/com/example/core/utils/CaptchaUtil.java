package com.example.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * redis公共工具类
 * @author daniel
 * @date 2019-01-08
 */
@Component
@Slf4j
public final class CaptchaUtil {

    private static final String mixDepository = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String numberDepository = "0123456789";

    /**
     * 产生验证码,采用4位验证码，数字和字母
     * @param count 返回几位验证码
     * @return
     */
    public String generateMix(Integer count) {
        String captcha = generate(mixDepository, count);
        return captcha;
    }

    /**
     * 产生验证码,采用4位验证码，单独数字
     * @param count 返回几位验证码
     * @return
     */
    public String generateNumber(Integer count) {
        String captcha = generate(numberDepository, count);
        return captcha;
    }

    private String generate(String depository, Integer count) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer("");
        Integer depositorySize = depository.length();
        for(int i = 0; i < count; i ++) {
            Integer index = random.nextInt(depositorySize);
            stringBuffer.append(depository.charAt(index));
        }
        return stringBuffer.toString();
    }
}
