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

    @Override
    public Corporation getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Corporation entity) {

    }
}
