package com.example.admin.controller.unit;

import com.example.admin.dto.response.OutputListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索引擎模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputListResult<Void> get() {
        return null;
    }
}
