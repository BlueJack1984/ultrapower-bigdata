package com.example.admin.controller;

import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Dictionary;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", tags= {"TestController"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final IDictionaryService dictionaryService;

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Dictionary> getById(@PathVariable("id") Long id) {

        return null;
    }

    public OutputListResult<Dictionary> getListByCondition(
            @RequestParam(value = "targetType", required = true) Integer targetType) throws ApplicationException {

        List<Dictionary> dictionaryList = null;
        return new OutputListResult<>(dictionaryList);
    }
}
