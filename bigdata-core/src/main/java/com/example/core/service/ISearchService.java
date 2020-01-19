package com.example.core.service;

import com.github.pagehelper.PageInfo;

/**
 * rabbitmq的服务模块
 * @author daniel
 * @date 2019-01-09
 */
public interface ISearchService {

    /**
     * 使用mysql产生序列号，作为redis中唯一key部分参数
     * 交易流水号
     */
    PageInfo<Void> search();
}
