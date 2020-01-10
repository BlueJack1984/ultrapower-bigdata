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

        //String tradeNumber = sequenceService.generateTradeNumber();
        String tradeNumber = "001";
        String captchaKey = "captcha:" + tradeNumber;
        return captchaKey;
    }

    /**
     *
     * @return
     */
    public String generateLoginKey(Long userId) {

        /**
         * login:userId:数值
         */
        if(null == userId) {
            //log.error(null, null);
            return null;
        }
        return userId.toString();
    }
}
