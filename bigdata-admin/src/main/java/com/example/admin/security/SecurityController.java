package com.example.admin.security;

import com.example.admin.dto.request.LoginInput;
import com.example.admin.dto.response.LoginResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import com.example.core.utils.DESUtil;
import com.example.core.utils.RedisKeyUtil;
import com.example.core.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 安全、登录等相关接口
 * @author daniel
 * @date 2019-12-24
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final IUserService userService;
    private final DESUtil desUtil;
    private final RedisKeyUtil redisKeyUtil;
    private final RedisUtil redisUtil;

    /**
     * 账号登录
     * 用户数据验证登录结果,生成token数据返回
     * 这里获取到前端发送过来的请求体，取出其中的用户名和密码，
     * 和数据库比对如果无误的话，签发token，并返回给前端。
     */
    @PostMapping("/login")
    public OutputResult<LoginResult> login(@RequestBody @Valid LoginInput loginInput) {

        String account = loginInput.getAccount();
        User user = userService.getByAccount(account);
        if(null == user) {
            //log.error(null, null);
            //返回用户不存在的code和message
            return new OutputResult<>(null, null);
        }
        String password = loginInput.getPassword();
        String encryptedPassword = desUtil.getEncryptString(password);
        if(! encryptedPassword.equals(user.getPassword())) {
            //log.error(null, null);
            //返回用户密码输入错误的code和message
            return new OutputResult<>(null, null);
        }
        //用户名和密码均正确
        LoginResult loginResult = new LoginResult();
        Long userId = user.getId();
        String loginKey = redisKeyUtil.generateLoginKey(userId);
        //存入到redis中，用户id作为key，account作为value
        redisUtil.set(loginKey, account, 100000);
        //将相关信息组装返回
        loginResult.setUserId(userId);
        loginResult.setTargetType(user.getTargetType());
        loginResult.setAccount(account);
        return new OutputResult<>(loginResult);
    }

    /**
     * 账号注销
     */
    @GetMapping("/logout")
    public OutputResult<String> logout(@RequestHeader(value = "_current_id") Long currentId) {
        //暂时没有业务，这里应该进行使token失效的操作
        return new OutputResult<>("用户成功退出：" + currentId);
    }

    /**
     * 账号注册
     * 用户数据验证登录结果,返回注册成功结果
     */
    @PostMapping("/register")
    public OutputResult<String> register(@RequestBody User user) {
        return null;
    }
}
