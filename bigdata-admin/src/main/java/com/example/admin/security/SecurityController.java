package com.example.admin.security;

import com.example.admin.dto.request.LoginInput;
import com.example.admin.dto.response.LoginResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import com.example.core.utils.DESUtil;
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

        }
        String password = loginInput.getPassword();
        String encryptedPassword = desUtil.getEncryptString(password);
        if(encryptedPassword.equals(user.getPassword())) {

        }
        return new OutputResult<>(null);
//        Map<String, Object> map = new HashMap<>();
//        String username = sysUser.getUsername();
//        String password = sysUser.getPassword();
//        if (sysUserService.login(username, password)){
//            String token = TokenUtil.sign(username,password);
//            if (token != null){
//                map.put("code", "10000");
//                map.put("message","认证成功");
//                map.put("token", token);
//                return null;
//            }
//        }
//        map.put("code", "00000");
//        map.put("message","认证失败");
        //return null;
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
