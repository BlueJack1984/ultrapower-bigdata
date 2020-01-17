package com.example.admin.controller;

import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.News;
import com.example.core.exception.ApplicationException;
import com.example.core.service.INewsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final INewsService newsService;

    public OutputListResult<News> get(
            @RequestParam(value="page", required=false, defaultValue="1")Integer pageNumber,
            @RequestParam(value="page", required=false, defaultValue="1")Integer pageSize) throws ApplicationException {

        PageInfo<News> pageInfo = newsService.getPage(pageNumber, pageSize);
        return new OutputListResult<>(pageInfo);
    }

    @GetMapping("/get/{id}")
    public OutputResult<News> getById(@PathVariable("id") Long id) throws ApplicationException{

        return null;
    }
}
