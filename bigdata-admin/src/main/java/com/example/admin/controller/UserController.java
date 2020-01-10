package com.example.admin.controller;

import com.example.admin.dto.request.UserInput;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    /**
     *
     * @return
     */
    @PostMapping("/add")
    public OutputResult<User> add(@RequestBody @Valid UserInput userInput) {

        User user = new User();
        user.setTargetType(userInput.getTargetType());
        user.setAccount(userInput.getAccount());
        user.setRealName(userInput.getRealName());
        user.setCorporationName(userInput.getCorporationName());
        user.setPosition(userInput.getPosition());
        user.setEmail(userInput.getEmail());

        List<String> businessCardUrlList = userInput.getBusinessCardUrlList();
        List<String> businessLicenseUrlList = userInput.getBusinessLicenseUrlList();
        User storedUser = userService.add(user, businessCardUrlList, businessLicenseUrlList);
        return new OutputResult<>(storedUser);
    }

    @PostMapping("/information/modify")
    public OutputResult<User> modifyInformation(@RequestBody @Valid UserInput userInput) {


        return null;
    }

    @PostMapping("/password/modify")
    public OutputResult<User> modifyPassword(@RequestBody @Valid UserInput userInput) {


        return null;
    }

    @GetMapping("/password/reset")
    public OutputResult<User> resetPassword(@RequestBody @Valid UserInput userInput) {

        return null;
    }

    @GetMapping("/status/modify")
    public OutputResult<User> modifyStatus(@RequestBody @Valid UserInput userInput) {

        return null;
    }
}
