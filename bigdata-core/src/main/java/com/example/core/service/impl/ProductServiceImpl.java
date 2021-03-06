package com.example.core.service.impl;

import com.example.core.dao.IProductDao;
import com.example.core.dao.IUserDao;
import com.example.core.entity.News;
import com.example.core.entity.Product;
import com.example.core.entity.User;
import com.example.core.service.IProductService;
import com.example.core.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductDao productDao;


    /****************************************************************************************
                                       基础服务
     ***************************************************************************************/


    /**
     * 根据id获取实体信息
     * @param id 实体id
     * @return 返回实体信息
     */
    @Override
    public Product getById(Long id) {

        Product product = productDao.selectById(id);
        return product;
    }

    @Override
    public void modifyById(Product entity) {

    }

    @Override
    public void add(Product entity) {

    }


    /****************************************************************************************
                                       定制服务
     ***************************************************************************************/
    @Override
    public List<Product> getListByIds(List<Long> productIds) {

        List<Product> productList = productDao.selectListByIds(productIds);
        return productList;
    }

    /**
     * 分页查询产品数据
     * @param keyword 要查询的关键词
     * @param offset 需要显示的页码
     * @param pageSize 每页显示的数量
     * @return
     */
    @Override
    public PageInfo<Product> getListByConditionPage(String keyword, Integer offset, Integer pageSize) {

        //分页查询
        Page<Product> page = PageHelper.startPage(offset, pageSize);
        List<Product> productList = productDao.getListByConditionPage(keyword);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }
}
