package com.example.core.service;

import com.example.core.entity.User;
import com.example.core.service.base.IBaseService;

import java.util.List;

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
     * @param user
     * @return
     */
    User add(User user, List<String> businessCardUrlList, List<String> businessLicenseUrlList);
    /**
     *
     * @return
     */
    List<User> getListAll();
    /**
     *
     * @return
     */
    User modify(User user);

}
