package com.example.admin.controller;

import com.example.core.exception.ApplicationException;
import com.example.core.service.ISequenceService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 生成唯一流水号，唯一交易号队列
 * @author daniel
 * @date 2019-01-14
 */
@Api(value="swagger测试", tags = {"TestController"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sequence")
public class SequenceController {

    private final ISequenceService sequenceService;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    @GetMapping("/get")
    public void get() throws ApplicationException {
        log.info("进入测试序列号模块！");
        try {
            SDF.parse("null");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        String name = "warrior";
        String number = sequenceService.generateTradeNumber();
        log.info("获取数据：" + number);
    }
}
