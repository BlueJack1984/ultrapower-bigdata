package com.example.core.service;

import com.example.core.entity.User;
import com.example.core.exception.ApplicationException;
import com.example.core.service.base.IBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户模块相关服务
 * @author daniel
 * @date 2019-01-10
 */
public interface IUserService extends IBaseService<Long, User> {

    /**
     *
     * @param account
     * @return
     */
    User getByAccount(String account);
    /**
     *
     * @return
     */
    List<User> getListAll();
    /**
     *
     * @param conditionMap
     * @return
     */
    PageInfo<User> getListByConditionPage(Map<String, Object> conditionMap) throws ApplicationException;

    /**
     *
     * @param user
     * @return
     */
    User add(User user, List<String> businessCardUrlList, List<String> businessLicenseUrlList);
    /**
     *
     * @param modifyMap
     * @return
     */
    User modifyInformation(Map<String, Object> modifyMap) throws ApplicationException;

}
