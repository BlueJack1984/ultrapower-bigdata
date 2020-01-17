package com.example.admin.controller;

import com.example.admin.dto.request.ProductInput;
import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Product;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 大数据产品模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="大数据产品模块接口", tags= {"大数据产品模块接口"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;


    /**
     * 根据用户id获取单个大数据产品信息
     * @param id 产品id
     * @return 产品信息
     */
    @ApiOperation(value="获取大数据产品",notes="根据id获取单个大数据产品信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="path", name = "id", value = "大数据产品id", required = true, dataType = "Long")})
    @CrossOrigin
    @GetMapping("/get/{id}")
    public OutputResult<Product> getById(@PathVariable("id") Long id) throws ApplicationException{

        //id值不为空，否则无法请求
        Product product = productService.getById(id);
        return new OutputResult<>(product);
    }


    /**
     * 新增大数据产品信息接口
     * @param productInput 新增的大数据产品信息
     *
     * @return 大数据产品信息
     */
    @ApiOperation(value="新增产品",notes="新增大数据产品信息接口")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="path", name = "id", value = "大数据产品id", required = true, dataType = "Long")})
    @CrossOrigin
    @PostMapping("/add")
    public OutputResult<Product> add(@RequestBody @Valid ProductInput productInput) throws ApplicationException{

        //productService.getPage(null, null);
        Product product = null;
        return new OutputResult<>(product);
    }

    /**
     * 根据创建时间获取最新产品列表
     * @return 产品信息
     */
    @ApiOperation(value="根据创建时间获取最新产品列表",notes="根据创建时间获取最新产品列表")
    @ApiImplicitParams({})
    @CrossOrigin
    @GetMapping("/get/list/page/latest")
    public OutputListResult<Product> getLatestListPage(
            @RequestParam(value = "offSet",required = false, defaultValue = "1") Integer offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws ApplicationException {

        PageInfo<Product> pageInfo = null;
        //调用服务接口，keyword参数赋值null
        pageInfo = productService.getListByConditionPage(null, offSet, pageSize);
        return new OutputListResult<>(pageInfo);
    }

    /**
     * 根据用户id获取单个产品信息
     * @param id 产品id
     * @return 产品信息
     */
    @ApiOperation(value="获取产品",notes="根据id获取单个产品信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="path", name = "id", value = "产品id", required = true, dataType = "Long")})
    @CrossOrigin
    @GetMapping("/get/list/page")
    public OutputListResult<Product> getListByConditionPage() throws ApplicationException {

        PageInfo<Product> pageInfo = null;
        return new OutputListResult<>(pageInfo);
    }
}
