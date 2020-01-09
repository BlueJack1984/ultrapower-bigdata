package com.example.admin.controller;

import com.example.admin.dto.response.CaptchaResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.service.IRabbitmqService;
import com.example.core.utils.CaptchaUtil;
import com.example.core.utils.RedisKeyUtil;
import com.example.core.utils.RedisUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取验证码模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/captcha")
public class CaptchaController {
    /**
     * redis的key操作工具类
     */
    private final RedisUtil redisUtil;
    /**
     * redis的key操作工具类
     */
    private final RedisKeyUtil redisKeyUtil;
    /**
     * redis的key操作工具类
     */
    private final CaptchaUtil captchaUtil;
    /**
     * redis的key操作工具类
     */
    private final IRabbitmqService rabbitmqService;

    @GetMapping("/generate/{count}")
    public OutputResult<CaptchaResult> generate(@PathVariable("count") Integer count) {

        CaptchaResult captchaResult = new CaptchaResult();
        String captchaKey = redisKeyUtil.generateCaptchaKey();
        captchaResult.setCaptchaKey(captchaKey);
        String captchaValue = captchaUtil.generateMix(count);
        captchaResult.setCaptchaValue(captchaValue);
        //获取的验证码需要通过短信发送到手机
        rabbitmqService.sendByFanout();
        //获取的验证码也需要存到redis中
        redisUtil.set(captchaKey, captchaValue, 1000);
        return new OutputResult(captchaResult);
    }
}
