package com.example.admin.controller;

import com.example.admin.dto.request.CategoryInput;
import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Category;
import com.example.core.exception.ApplicationException;
import com.example.core.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 分类模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", tags= {"TestController"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Category> getById(@PathVariable("id") Long id) throws ApplicationException {
        Category category = null;
        return new OutputResult<>(category);
    }

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/list/condition")
    public OutputListResult<Category> getListByCondition(
            @RequestParam(value = "targetType", required = true) Integer targetType) throws ApplicationException{

        //按照类别查询数据，如领域、技术框架等
        List<Category> categoryList = null;
        return new OutputListResult<>(categoryList);
    }

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @PostMapping("/add")
    public OutputResult<Category> add(@RequestBody @Valid CategoryInput categoryInput) throws ApplicationException {

        Category category = null;
        return new OutputResult<>(category);
    }

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @PostMapping("/modify/information")
    public OutputResult<Category> modifyInformation(@RequestBody @Valid CategoryInput categoryInput) throws ApplicationException {

        Category category = null;
        return new OutputResult<>(category);
    }
}
