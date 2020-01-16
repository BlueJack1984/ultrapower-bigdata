package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.ApplicationCase;
import com.example.core.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IApplicationCaseDao extends IBaseDao<Long, ApplicationCase> {

    /**
     *
     * @param applicationCaseIds
     * @return
     */
    List<ApplicationCase> selectListByIds(@Param("applicationCaseIds") List<Long> applicationCaseIds);
}
