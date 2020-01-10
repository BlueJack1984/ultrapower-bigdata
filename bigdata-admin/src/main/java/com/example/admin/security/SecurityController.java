package com.example.admin.security;

import com.example.admin.dto.request.LoginInput;
import com.example.admin.dto.request.RegisterInput;
import com.example.admin.dto.response.LoginResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import com.example.core.utils.DESUtil;
import com.example.core.utils.PhoneFormatCheckUtil;
import com.example.core.utils.RedisKeyUtil;
import com.example.core.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    private final PhoneFormatCheckUtil phoneFormatCheckUtil;

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
    public OutputResult<String> logout(@RequestHeader(value = "currentUserId") Long currentUserId) {
        //删除redis中记录的登录信息键值对
        String redisLoginKey = currentUserId.toString();
        redisUtil.delete(redisLoginKey);
        return new OutputResult<>("用户成功退出：" + currentUserId);
    }

    /**
     * 账号注册,只能是企业账号
     * 用户数据验证登录结果,返回注册成功结果
     */
    @PostMapping("/register")
    public OutputResult<Void> register(@RequestBody @Valid RegisterInput registerInput) {

        //手机号作为账号，格式验证
        String phoneNumber = registerInput.getPhoneNumber();
        try {
            Boolean validationResult = phoneFormatCheckUtil.isPhoneLegal(phoneNumber);
            if(! validationResult) {
                //log.error(null, null);
                return new OutputResult<>(201, "");
            }
        }catch (Exception ex) {
            //log.error(null, null);
            return new OutputResult<>();
        }
        //手机号是否已经注册
        List<User> userList = userService.getListAll();
        if(null != userList && userList.size() > 0) {
            //遍历是否有已注册手机号
            for(User user : userList) {
                String account = user.getAccount();
                if(phoneNumber.equals(account)) {
                    //log.error(null, null);
                    return new OutputResult<>(201, "");
                }
            }
        }
        //输入密码与确认密码是否一致
        String password = registerInput.getPassword();
        String confirmPassword = registerInput.getConfirmPassword();
        if(! password.equals(confirmPassword)) {
            //log.error(null, null);
            return new OutputResult<>(202, "");
        }
        //验证码是否正确
        String captcha = registerInput.getCaptcha();
        String storedCaptcha = redisUtil.get(registerInput.getCaptchaKey(), String.class);
        if(! captcha.equals(storedCaptcha)) {
            //log.error(null, null);
            return new OutputResult<>(202, "");
        }
        //各项信息正确
        //密码加密
        String encryptedPassword = desUtil.getEncryptString(password);
        //将注册信息存入数据库
        User user = new User();
        user.setAccount(phoneNumber);
        user.setPassword(encryptedPassword);
        user.setStatus(0);
        user.setTargetType(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //userService.add(user);
        //将redis中的对应键值对删除, 不删除也可以，到期自动消失
        redisUtil.delete(registerInput.getCaptchaKey());
        //返回成功结果
        return new OutputResult<>();
    }
}
