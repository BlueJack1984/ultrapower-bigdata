package com.example.core.service.impl;

import com.example.core.dao.INewsDao;
import com.example.core.entity.News;
import com.example.core.service.INewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements INewsService {

    private final INewsDao newsDao;


    @Override
    public News getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(News entity) {

    }

    @Override
    public void add(News entity) {

    }
}
