package com.example.core.service.impl;

import com.example.core.dao.IProductDao;
import com.example.core.dao.IUserDao;
import com.example.core.entity.News;
import com.example.core.entity.Product;
import com.example.core.entity.User;
import com.example.core.service.IProductService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductDao productDao;

    @Override
    public PageInfo<Product> getPage(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Product entity) {

    }

    @Override
    public void add(Product entity) {

    }
}
