package com.example.core.service.impl;

import com.example.core.dao.IUserDao;
import com.example.core.entity.News;
import com.example.core.entity.PolicyInformation;
import com.example.core.entity.User;
import com.example.core.service.IPolicyInformationService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyInformationServiceImpl implements IPolicyInformationService {


    @Override
    public PageInfo<PolicyInformation> getPage(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PolicyInformation getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(PolicyInformation entity) {

    }

    @Override
    public void add(PolicyInformation entity) {

    }
}
