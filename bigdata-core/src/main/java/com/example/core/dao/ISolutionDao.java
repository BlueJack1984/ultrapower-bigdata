package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.News;
import com.example.core.entity.Solution;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISolutionDao extends IBaseDao<Long, Solution> {

}
