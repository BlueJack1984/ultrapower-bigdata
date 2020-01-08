package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Category;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/get/{id}")
    public OutputResult<Category> getById(@PathVariable("id") Long id) {
        Category category = null;
        return new OutputResult<>(category);
    }
}
