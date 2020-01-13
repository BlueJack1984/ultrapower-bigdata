package com.example.core.service;

import com.example.core.entity.Corporation;
import com.example.core.entity.News;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

public interface ICorporationService extends IBaseService<Long, Corporation> {

    PageInfo<Corporation> getPage(Integer pageNumber, Integer pageSize);
}
