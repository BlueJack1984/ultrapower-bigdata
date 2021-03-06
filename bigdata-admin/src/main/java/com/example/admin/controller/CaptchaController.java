package com.example.admin.controller;

import com.example.admin.dto.response.CaptchaResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IRabbitmqService;
import com.example.core.utils.CaptchaUtil;
import com.example.core.utils.RedisKeyUtil;
import com.example.core.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 获取验证码模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", tags = {"TestController"})
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


    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/generate/{count}")
    public OutputResult<CaptchaResult> generate(@PathVariable("count") Integer count) throws ApplicationException {

        CaptchaResult captchaResult = new CaptchaResult();
        String captchaKey = redisKeyUtil.generateCaptchaKey();
        captchaResult.setCaptchaKey(captchaKey);
        String captchaValue = captchaUtil.generateMix(count);
        captchaResult.setCaptchaValue(captchaValue);
        //获取的验证码需要通过短信发送到手机
        //占位置
        rabbitmqService.sendByFanout();
        //获取的验证码也需要存到redis中
        redisUtil.set(captchaKey, captchaValue, 1000);
        return new OutputResult(captchaResult);
    }
}
