package com.example.core.service;

import com.example.core.entity.User;
import com.example.core.service.base.IBaseService;

public interface IUserService extends IBaseService<Long, User> {

    User getByAccount(String account);

    User add(User user);
}
