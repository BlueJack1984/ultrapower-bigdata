package com.example.core.service.impl;

import com.example.core.dao.IUserDao;
import com.example.core.entity.Hotspot;
import com.example.core.entity.News;
import com.example.core.entity.User;
import com.example.core.service.IHotspotService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotspotServiceImpl implements IHotspotService {

    private final IUserDao userDao;


    @Override
    public PageInfo<News> getPage(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Hotspot getById(Long id) {
        return null;
    }
}
