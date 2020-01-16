package com.example.admin.controller;


import com.example.admin.dto.response.OutputResult;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IFashionCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 新品速递模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="新品速递模块", tags = {"新品速递模块"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/fashion")
public class FashionCatalogController {


    private final IFashionCatalogService fashionCatalogService;

    /**
     * 分页查询新品速递的最新数据列表
     * @param keyword 输入的查询关键词
     * @param offSet 查询的页码
     * @param pageSize 每页显示的数据条数
     * @return 新品速递最新列表信息
     */
    @ApiOperation(value = "新品速递模块", notes = "分页查询新品速递的最新数据列表")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "keyword", value = "查询关键词", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "offSet", value = "查询的页码", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页显示的数据条数", required = false)
    })
    @CrossOrigin
    @GetMapping("/get/list/condition/page")
    public OutputResult<Map<String, Object>> getListByConditionPage(
            @RequestParam(value = "keyword",required = false) String keyword,
            @RequestParam(value = "offSet",required = false, defaultValue = "1") Integer offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws ApplicationException {

        //定义返回的map，key为实体类型，value为实体列表
        Map<String, Object> fashionResultMap = null;
        fashionResultMap = fashionCatalogService.getListByConditionPage(keyword, offSet, pageSize);
        return new OutputResult<>(fashionResultMap);
    }
}
