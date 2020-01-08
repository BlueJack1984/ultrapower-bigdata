package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserDao extends IBaseDao<Long, User> {

    User selectByPrimaryKey(@Param("id") Long id);

    User selectByAccount(@Param("account") String id);
}