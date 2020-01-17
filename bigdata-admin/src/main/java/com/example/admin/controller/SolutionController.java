package com.example.admin.controller;

import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Solution;
import com.example.core.exception.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 解决方案模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/solution")
public class SolutionController {

    /**
     * 上传图片模块
     * @param imageRequest 请求头包含图片信息
     * @param path 上传oss的文件夹
     * @param targetType 实体类型
     * @param checkFlag 是否要检查上传文件尺寸
     * @throws ApplicationException 上传异常
     */
    @ApiOperation(value = "上传并审核图片", notes = "图片上传至oss")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "request", value = "请求头包含图片信息", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "targetType", value = "实体类型", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "checkFlag", value = "是否要检查上传文件尺寸")
    })
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Solution> getById(@PathVariable("id") Long id) throws ApplicationException {

        return null;
    }

    /**
     * 上传图片模块
     * @param imageRequest 请求头包含图片信息
     * @param path 上传oss的文件夹
     * @param targetType 实体类型
     * @param checkFlag 是否要检查上传文件尺寸
     * @throws ApplicationException 上传异常
     */
    @ApiOperation(value = "上传并审核图片", notes = "图片上传至oss")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "request", value = "请求头包含图片信息", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "targetType", value = "实体类型", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "checkFlag", value = "是否要检查上传文件尺寸")
    })
    @CrossOrigin
    @GetMapping("/get/list/page")
    public OutputListResult<Solution> getListPage() throws ApplicationException {
        return null;
    }
}
