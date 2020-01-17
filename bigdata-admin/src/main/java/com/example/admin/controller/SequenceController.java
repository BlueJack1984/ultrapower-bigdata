package com.example.admin.controller;

import com.example.core.exception.ApplicationException;
import com.example.core.service.ISequenceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
