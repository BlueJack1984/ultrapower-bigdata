package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISequenceDao extends IBaseDao<Long, Sequence> {

}
