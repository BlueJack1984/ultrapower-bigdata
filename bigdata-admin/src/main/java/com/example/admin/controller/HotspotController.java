package com.example.admin.controller;

import com.example.admin.dto.request.HotspotInput;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Hotspot;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IHotspotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/audit")
public class HotspotController {

    private final IHotspotService hotspotService;

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
    public OutputResult<Hotspot> getById(@PathVariable("id") Long id) throws ApplicationException {

        //判断参数是否为空
        if(null == id) {
            log.info("");
            return new OutputResult<>(201, "查询hotspot的id参数为空");
        }
        Hotspot hotspot = hotspotService.getById(id);
        return new OutputResult<>(hotspot);
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
    @PostMapping("/add")
    public OutputResult<Hotspot> add(@RequestBody @Valid HotspotInput hotspotInput) throws ApplicationException{
        Hotspot hotspot = null;
        return new OutputResult<>(hotspot);
    }
}
