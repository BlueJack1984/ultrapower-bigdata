package com.example.core.service.impl;

import com.example.core.service.ISearchService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * rabbitmq的服务
 * @author daniel
 * @date 2019-01-09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements ISearchService {


    @Override
    public PageInfo<Void> search() {
        return null;
    }
}
