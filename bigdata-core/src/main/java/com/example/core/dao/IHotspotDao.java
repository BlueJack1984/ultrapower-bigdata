package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.Hotspot;
import com.example.core.entity.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHotspotDao extends IBaseDao<Long, Hotspot> {

}
