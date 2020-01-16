package com.example.core.service;

import com.example.core.entity.News;
import com.example.core.entity.Product;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IProductService extends IBaseService<Long, Product> {

    PageInfo<Product> getPage(Integer pageNumber, Integer pageSize);

    /**
     *
     * @param productIds
     * @return
     */
    List<Product> getListByIds(List<Long> productIds);
}
