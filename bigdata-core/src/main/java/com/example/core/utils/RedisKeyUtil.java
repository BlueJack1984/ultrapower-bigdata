package com.example.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * redis的键key操作工具类
 * @author daniel
 * @date 2019-01-08
 */
@Component
@Slf4j
public final class RedisKeyUtil {

    /**
     *
     * @return
     */
    public String generateCaptchaKey() {

        /**
         * captcha:id:数值
         */
        return null;
    }

    /**
     *
     * @return
     */
    public String generateLoginKey() {

        /**
         * login:userId:数值
         */
        return null;
    }
}
