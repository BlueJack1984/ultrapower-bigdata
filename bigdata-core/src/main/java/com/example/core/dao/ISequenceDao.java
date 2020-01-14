package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ISequenceDao extends IBaseDao<Long, Sequence> {

    /**
     *
     * @param sequenceName
     * @return
     */
    Integer generateTradeNumber(@Param("sequenceName") String sequenceName);
}
