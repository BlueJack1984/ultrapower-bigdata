package com.example.core.utils;

import com.example.core.service.ISequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * redis的键key操作工具类
 * @author daniel
 * @date 2019-01-08
 */
@Component
@Slf4j
@RequiredArgsConstructor
public final class RedisKeyUtil {

    private final ISequenceService sequenceService;

    /**
     * @return
     */
    public String generateCaptchaKey() {

        String tradeNumber = sequenceService.generateTradeNumber();
        String captchaKey = "captcha:" + tradeNumber;
        return captchaKey;
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
