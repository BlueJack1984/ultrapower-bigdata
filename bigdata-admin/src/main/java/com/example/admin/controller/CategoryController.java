package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", tags= {"TestController"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Category> getById(@PathVariable("id") Long id) {
        Category category = null;
        return new OutputResult<>(category);
    }
}
