package com.example.admin.controller;

import com.example.admin.dto.response.CaptchaResult;
import com.example.admin.dto.response.OutputResult;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/generate")
    public OutputResult<CaptchaResult> generate() {

        CaptchaResult captchaResult = new CaptchaResult();
        captchaResult.setCaptchaKey();
        captchaResult.setCaptchaValue();
        return new OutputResult(captchaResult);
    }

}
