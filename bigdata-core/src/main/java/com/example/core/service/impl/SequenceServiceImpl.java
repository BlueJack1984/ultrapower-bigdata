package com.example.core.service.impl;

import com.example.core.dao.ISequenceDao;
import com.example.core.service.ISequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class SequenceServiceImpl implements ISequenceService {

    private final ISequenceDao sequenceDao;
    private static final String SEQUENCE_NAME = "warrior";
    /**
     * 日期格式转换
     */
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 使用mysql产生序列号，作为redis中唯一key部分参数
     * 交易流水号
     */
    @Override
    public String generateTradeNumber() {

        Integer sequenceNumber = sequenceDao.generateTradeNumber(SEQUENCE_NAME);
        String sequenceString = Integer.toString(sequenceNumber);
        //大于等于6位需要截取，小于6位前面补0，保证6位
        Integer length = sequenceString.length();
        if(length >= 6) {
            sequenceString = sequenceString.substring(length - 6, length);
        }else {
            sequenceString = paddingHeaderZero(sequenceString, 6);
        }
        String currentTime = SDF.format(new Date());
        return currentTime + sequenceString;
    }

    /**
     * 使用mysql产生序列号，作为redis中唯一key部分参数
     * @param source
     * @param destinationSize
     * @return
     * 交易流水号
     */
    private String paddingHeaderZero(String source, Integer destinationSize) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer sourceSize = source.length();
        Integer offset = destinationSize - sourceSize;
        if(offset < 0) {
            log.info("");
            return null;
        }
        //补0
        for(int i = 0; i < offset; i ++) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString() + source;
    }
}
