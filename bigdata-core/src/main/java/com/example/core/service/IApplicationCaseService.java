package com.example.core.service;

import com.example.core.entity.ApplicationCase;
import com.example.core.entity.News;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IApplicationCaseService extends IBaseService<Long, ApplicationCase> {




    List<ApplicationCase> getListByIds(List<Long> applicationCaseIds);
}
