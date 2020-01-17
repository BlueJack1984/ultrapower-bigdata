package com.example.admin.controller;

import com.example.admin.dto.request.CorporationInput;
import com.example.admin.dto.response.OutputResult;
import com.example.core.constants.ResponseCode;
import com.example.core.entity.Corporation;
import com.example.core.exception.ApplicationException;
import com.example.core.service.ICorporationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 新增公司信息
     * @param corporationInput 新增信息
     * @return 返回公司信息
     */
    @ApiOperation(value="新增公司信息",notes="新增公司信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="body", name = "corporationInput", value = "新增公司信息", required = true, dataType = "CorporationInput")})
    @CrossOrigin
    @PostMapping("/add")
    public OutputResult<Corporation> add(@RequestBody @Valid CorporationInput corporationInput) throws ApplicationException{

        //首先判断该公司信息是否已经存在
        String registrationCode = corporationInput.getRegistrationCode();
        Corporation corporation = corporationService.getByCode(registrationCode);
        if(null != corporation) {
            log.error("【公司模块：新增公司信息接口-要新增的公司信息已经存在】");
            return new OutputResult<>(ResponseCode.CORPORATION_ALREADY_EXIST_ERROR);
        }
        //新增信息
        //处理相关数据，如格式转换等
        String corporationName = corporationInput.getCorporationName();
        Corporation storedCorporation = corporationService.addInformation(corporationName);
        return new OutputResult<>(storedCorporation);
    }

    /**
     * 根据id修改公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @ApiOperation(value="根据id修改公司信息",notes="根据id修改公司信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="body", name = "corporationInput", value = "根据id修改公司信息", required = true, dataType = "CorporationInput")})
    @CrossOrigin
    @PostMapping("/modify/information/{id}")
    public OutputResult<Corporation> modifyInformation(
            @PathVariable("id") Long id,
            @RequestBody @Valid CorporationInput corporationInput) throws ApplicationException{

        //首先判断该公司信息是否已经存在
        Corporation corporation = corporationService.getById(id);
        if(null == corporation) {
            log.error("【公司模块：修改公司信息接口-要修改的公司信息不存在】");
            return new OutputResult<>(ResponseCode.CORPORATION_NOT_EXIST_ERROR);
        }
        //修改公司信息
        //处理相关数据，如更新属性等
        String corporationName = corporationInput.getCorporationName();
        Corporation storedCorporation = corporationService.addInformation(corporationName);
        return new OutputResult<>(storedCorporation);
    }
}
