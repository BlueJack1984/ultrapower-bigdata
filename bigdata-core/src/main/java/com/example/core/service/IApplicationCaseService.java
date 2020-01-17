package com.example.core.service;

import com.example.core.entity.ApplicationCase;
import com.example.core.entity.News;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
public interface IApplicationCaseService extends IBaseService<Long, ApplicationCase> {




    List<ApplicationCase> getListByIds(List<Long> applicationCaseIds);
}
