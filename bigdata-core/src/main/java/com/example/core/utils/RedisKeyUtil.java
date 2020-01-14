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
     *
     * @return
     */
    public String generateCaptchaKey() {

        String tradeNumber = sequenceService.generateTradeNumber();
        //String tradeNumber = "001";
        String captchaKey = "captcha:" + tradeNumber;
        return captchaKey;
    }

    /**
     * 产生登录信息在redis中的存储key，使用用户id作为key
     * @param userId 用户id参数
     * @return 返回获取到的登录信息存储在redis中的字符串key
     */
    public String generateLoginKey(Long userId) {

        //判断userId是否为空
        if(null == userId) {
            log.error("【user：登录接口中-产生redis的key错误，传入的userId为空】");
            return null;
        }
        //这里需要进行产生交易流水号来区分多个用户
        //格式为userId：tradeNumber
        String tradeNumber = sequenceService.generateTradeNumber();
        return userId.toString() + ":" + tradeNumber;
    }
}
