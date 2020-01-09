package com.example.core.service;

/**
 * sequence产生序列号模块
 * @author daniel
 * @date 2019-01-09
 */
public interface ISequenceService {

    /**
     * 使用mysql产生序列号，作为redis中唯一key部分参数
     * 交易流水号
     */
    String generateTradeNumber();
}
