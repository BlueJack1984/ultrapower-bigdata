package com.example.admin.controller.unit;

import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 个人中心模块
 * @author daniel
 * @date 2019-12-30
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/personal")
public class PersonalController {

    /**
     * 修改个人信息
     * @param
     * @return
     */
    @PostMapping("/modify/information")
    public OutputResult<User> modifyInformation() {
        return new OutputResult<>();
    }

    /**
     * 修改登录密码
     * @param
     * @return
     */
    @PostMapping("/modify/password")
    public OutputResult<Void> modifyPassword() {
        return new OutputResult<>();
    }

    @GetMapping("/get/{id}")
    public OutputResult<User> getById(@PathVariable("id") Long id) {

        return null;
    }
}
