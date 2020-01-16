package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.FashionCatalog;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新品速递dao模块
 * @author daniel
 * @date 2019-01-16
 */
@Mapper
public interface IFashionCatalogDao extends IBaseDao<Long, FashionCatalog> {

    /**
     *
     * @return
     */
    List<FashionCatalog> selectListByConditionPage(@Param("keyword") String keyword);
}
