package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 用户dao模块
 * @author daniel
 * @date 2019-01-13
 */
@Mapper
public interface IUserDao extends IBaseDao<Long, User> {

    /**
     * 根据用户账号查询用户信息
     * @param account 用户账号
     * @return 查询到的用户实体
     */
    User selectByAccount(@Param("account") String account);

    /**
     * 获取所有的用户列表
     * @return 查询到的用户实体
     */
    List<User> selectListAll();

    /**
    * 多条件查询用户列表
    * @param searchDateStart 用户的创建时间查询起始点
    * @param searchDateEnd 用户的创建时间查询结束点
    * @param keyword 查询关键词
    * @return 查询到的用户实体列表
    */
    List<User> selectListByConditionPage(@Param("searchDateStart") Date searchDateStart,
                                         @Param("searchDateEnd") Date searchDateEnd,
                                         @Param("keyword")String keyword);
}