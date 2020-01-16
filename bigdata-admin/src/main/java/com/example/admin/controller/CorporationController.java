package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Corporation;
import com.example.core.service.ICorporationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 公司模块操作
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="公司模块业务操作", tags = {"公司模块业务操作"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/corporation")
public class CorporationController {

    private final ICorporationService corporationService;

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value="根据id获取特定公司信息",notes="根据id获取特定公司信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="path", name = "id", value = "公司id", required = true, dataType = "Long")})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Corporation> getById(@PathVariable("id") Long id) {

        Corporation corporation = corporationService.getById(id);
        return new OutputResult<>(corporation);
    }
}
