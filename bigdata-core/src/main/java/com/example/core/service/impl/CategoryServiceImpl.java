package com.example.core.service.impl;

import com.example.core.dao.ICategoryDao;
import com.example.core.dao.IUserDao;
import com.example.core.entity.Category;
import com.example.core.entity.News;
import com.example.core.entity.User;
import com.example.core.service.ICategoryService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分类服务模块
 * @author daniel
 * @date 2019-12-30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao;



    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Category entity) {

    }

    @Override
    public void add(Category entity) {

    }
}
