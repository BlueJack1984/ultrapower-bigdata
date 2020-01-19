package com.example.admin.controller.unit;

import com.example.admin.dto.response.OutputListResult;
import com.example.core.exception.ApplicationException;
import com.example.core.service.ISearchService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索引擎模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="搜索引擎模块", tags = {"搜索引擎模块全品类搜索"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {


    private final ISearchService searchService;

    /**
     * 添加单个用户信息
     * @param userInput 添加用户的输入信息
     * targetType 用户类型：企业用户，数据审核员，管理员
     * account 账号
     * realName 真实姓名
     * corporationName 公司名称
     * position 公司职位
     * email 邮箱
     * businessCardUrlList 个人名片
     * businessLicenseUrlList 营业执照
     * @return 添加后的user信息，用于回显
     */
    @ApiOperation(value = "搜索引擎模块", notes = "搜索引擎模块全品类搜索")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "targetType", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "primaryCategoryId", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "secondaryCategoryId", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "applicationFieldId", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "districtId", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "certificationStatus", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "keyword", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "offSet", value = "登录输入参数", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "pageSize", value = "登录输入参数", required = true)
    })
    @CrossOrigin
    @PostMapping("/whole")
    public OutputListResult<Void> search(
            @RequestParam(value = "targetType", required = false) Integer targetType,
            @RequestParam(value = "primaryCategoryId", required = false) Long primaryCategoryId,
            @RequestParam(value = "secondaryCategoryId", required = false) Long secondaryCategoryId,
            @RequestParam(value = "applicationFieldId", required = false) Long applicationFieldId,
            @RequestParam(value = "districtId", required = false) Long districtId,
            @RequestParam(value = "certificationStatus", required = false) Integer certificationStatus,
            @RequestParam(value = "keyword", required = false) Integer keyword,
            @RequestParam(value = "offSet",required = false, defaultValue = "1") Integer offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws ApplicationException {



        PageInfo<Void> pageInfo = null;
        searchService.search();
        return new OutputListResult<>(pageInfo);
    }
}
