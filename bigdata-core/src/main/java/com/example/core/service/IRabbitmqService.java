package com.example.core.service;

/**
 * rabbitmq的服务模块
 * @author daniel
 * @date 2019-01-09
 */
public interface IRabbitmqService {

    void sendByTopic();

    void sendByFanout();

    void sendByDirect();
}
