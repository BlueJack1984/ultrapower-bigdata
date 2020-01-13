package com.example.core.service;

import com.example.core.entity.Category;
import com.example.core.entity.News;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

public interface ICategoryService extends IBaseService<Long, Category> {

    PageInfo<Category> getPage(Integer pageNumber, Integer pageSize);
}
