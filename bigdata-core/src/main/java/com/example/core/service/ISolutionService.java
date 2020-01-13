package com.example.core.service;

import com.example.core.entity.News;
import com.example.core.entity.Solution;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

public interface ISolutionService extends IBaseService<Long, Solution> {

    PageInfo<Solution> getPage(Integer pageNumber, Integer pageSize);
}
