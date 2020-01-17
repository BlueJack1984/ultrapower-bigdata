package com.example.core.service;

import com.example.core.entity.Corporation;
import com.example.core.entity.News;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

/**
 * 公司业务相关模块
 * @author daniel
 * @date 2019-01-15
 */
public interface ICorporationService extends IBaseService<Long, Corporation> {


    /**
     * 根据公司名称获取公司实体，精确查询
     * @param corporationName
     * @return
     */
    Corporation getByName(String corporationName);

    Corporation getByCode(String registrationCode);
    /**
     *
     * @param corporationName
     * @return
     */
    //暂时不成熟，需要复用，后面考虑
    Corporation addInformation(String corporationName);




}
