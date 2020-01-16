package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.News;
import com.example.core.entity.Solution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISolutionDao extends IBaseDao<Long, Solution> {


    /**
     *
     * @param solutionIds
     * @return
     */
    List<Solution> selectListByIds(@Param("solutionIds") List<Long> solutionIds);

}
