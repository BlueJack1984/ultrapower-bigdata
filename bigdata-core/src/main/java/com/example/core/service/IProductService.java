package com.example.core.service;

import com.example.core.entity.News;
import com.example.core.entity.Product;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
public interface IProductService extends IBaseService<Long, Product> {

    /**
     *
     * @param productIds
     * @return
     */
    List<Product> getListByIds(List<Long> productIds);

    /**
     * 分页查询产品数据
     * @param keyword 要查询的关键词
     * @param offset 需要显示的页码
     * @param pageSize 每页显示的数量
     * @return
     */
    PageInfo<Product> getListByConditionPage(String keyword, Integer offset, Integer pageSize);
}
