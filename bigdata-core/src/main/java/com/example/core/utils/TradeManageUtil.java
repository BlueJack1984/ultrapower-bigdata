package com.example.core.utils;

import com.example.core.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * token工具类
 * @author daniel
 * @date 2019-12-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public final class TradeManageUtil {

    private final IProductService productService;
    /**
     * 日期格式转换
     */
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 生成交易流水号
     *
     * @return
     */
//    public String getOtaTradeNo() {
//        Long nextVal = assetManageBusiDao.getOtaTradeNo();
//        String tempId = Long.toString(nextVal);
//        if (tempId.length() > 6) {
//            tempId = tempId.substring(tempId.length() - 6, tempId.length());
//        } else {
//            tempId = StringUtil.paddingHeadZero(tempId, 6);
//        }
//
//        String sysTimeStr = DateUtils.format(new Date(), "yyyyMMddHHmmss");
//        String tradeId = sysTimeStr + tempId;
//        return tradeId;
//    }
}
