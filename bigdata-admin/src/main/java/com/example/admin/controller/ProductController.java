package com.example.admin.controller;

import com.example.admin.dto.request.ProductInput;
import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Product;
import com.example.core.service.IProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 产品模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/get/{id}")
    public OutputResult<Product> getById(@PathVariable("id") Long id) {

        return null;
    }

    @PostMapping("/add")
    public OutputResult<Product> add(@RequestBody @Valid ProductInput productInput) {

        productService.getPage(null, null);
        Product product = null;
        return new OutputResult<>(product);
    }

    /**
     * 获取列表
     */
    @GetMapping("/get/list")
    public OutputListResult<Product> getList() {

        List<Product> productList = null;

        return new OutputListResult<>(productList);
    }

    /**
     * 获取列表
     * 分页模式
     */
    @GetMapping("/get/list/page")
    public OutputListResult<Product> getListPage() {
        PageInfo<Product> pageInfo = null;
        return new OutputListResult<>(pageInfo);
    }

}
