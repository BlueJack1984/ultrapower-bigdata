package com.example.core.service.impl;

import com.example.core.dao.IUserDao;
import com.example.core.entity.Corporation;
import com.example.core.entity.News;
import com.example.core.entity.User;
import com.example.core.service.ICorporationService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CorporationServiceImpl implements ICorporationService {

    private final IUserDao userDao;


    @Override
    public PageInfo<Corporation> getPage(Integer pageNumber, Integer pageSize) {
        return null;
    }

    /**
     * 根据公司名称获取公司实体，精确查询
     * @param corporationName
     * @return
     */
    @Override
    public Corporation getByName(String corporationName) {
        return null;
    }

    @Override
    public Corporation getByCode(String registrationCode) {
        return null;
    }

    @Override
    public Corporation addInformation(String corporationName) {
        return null;
    }


    @Override
    public Corporation getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Corporation entity) {
        int i = 10;
        System.out.println(i);
    }

    @Override
    public void add(Corporation entity) {

    }
}
