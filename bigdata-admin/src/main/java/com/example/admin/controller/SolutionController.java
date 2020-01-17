package com.example.admin.controller;

import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Solution;
import com.example.core.exception.ApplicationException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get/{id}")
    public OutputResult<Solution> getById(@PathVariable("id") Long id) throws ApplicationException {

        return null;
    }

    @GetMapping("/get/list/page")
    public OutputListResult<Solution> getListPage() throws ApplicationException {
        return null;
    }
}
