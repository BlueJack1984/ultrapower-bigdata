package com.example.admin.controller;

import com.example.core.service.ISequenceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get")
    public void get() {
        log.info("进入测试序列号模块！");
        String name = "warrior";
        String number = sequenceService.generateTradeNumber();
        log.info("获取数据：" + number);
    }
}
