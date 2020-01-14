package com.example.core.service.impl;

import com.example.core.dao.ISequenceDao;
import com.example.core.service.ISequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SequenceServiceImpl implements ISequenceService {

    private final ISequenceDao sequenceDao;

    /**
     * 使用mysql产生序列号，作为redis中唯一key部分参数
     * 交易流水号
     */
    @Override
    public String generateTradeNumber() {
        sequenceDao.selectById(null);
        return null;
    }
}
