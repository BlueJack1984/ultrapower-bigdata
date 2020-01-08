package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.Corporation;
import com.example.core.entity.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICorporationDao extends IBaseDao<Long, Corporation> {

}
