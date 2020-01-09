package com.example.core.service.impl;

import com.example.core.service.IRabbitmqService;
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
public class RabbitmqServiceImpl implements IRabbitmqService {
    /**
     *
     */
    @Override
    public void sendByTopic() {

    }

    /**
     *
     */
    @Override
    public void sendByFanout() {

    }

    /**
     *
     */
    @Override
    public void sendByDirect() {

    }
}
