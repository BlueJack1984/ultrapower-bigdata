package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @ApiOperation(value="获取用户",notes="根据id获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="path", name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return user;
    }

    @PostMapping("/add")
    public OutputResult<User> add() {
        userService.add(null);
        return new OutputResult<>(null);
    }

    @PostMapping("/modify")
    public OutputResult<User> modify(@RequestBody User user) {
        return null;
    }
}
