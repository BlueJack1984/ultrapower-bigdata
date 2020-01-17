package com.example.core.service.impl;

import com.example.core.dao.IApplicationCaseDao;
import com.example.core.dao.IUserDao;
import com.example.core.entity.ApplicationCase;
import com.example.core.entity.News;
import com.example.core.entity.User;
import com.example.core.service.IApplicationCaseService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 成功案例服务模块
 * @author daniel
 * @date 2019-12-30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationCaseServiceImpl implements IApplicationCaseService {

    private final IApplicationCaseDao applicationCaseDao;



    @Override
    public ApplicationCase getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(ApplicationCase entity) {

    }

    @Override
    public void add(ApplicationCase entity) {

    }


    @Override
    public List<ApplicationCase> getListByIds(List<Long> applicationCaseIds) {

        List<ApplicationCase> applicationCaseList = applicationCaseDao.selectListByIds(applicationCaseIds);
        return applicationCaseList;
    }
}
