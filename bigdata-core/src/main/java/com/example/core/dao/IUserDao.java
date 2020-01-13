package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户模块的mapper
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
    * 根据用户账号查询用户信息
    * @param account 用户账号
    * @return 查询到的用户实体
    */
    List<User> selectListByConditionPage();
}