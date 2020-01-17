package com.example.core.service;

import com.example.core.entity.FashionCatalog;
import com.example.core.entity.Hotspot;
import com.example.core.exception.ApplicationException;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 新品速递表
 * @author daniel
 * @date 2019-01-16
 */
public interface IFashionCatalogService extends IBaseService<Long, FashionCatalog> {

    /**
     *
     * @param keyword
     * @param offset
     * @param pageSize
     * @return
     */
    Map<String, Object> getListByConditionPage(String keyword, Integer offset, Integer pageSize) throws ApplicationException;
}
