package com.example.core.service.impl;

import com.example.core.dao.IUserDao;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDao userDao;

    @Override
    public User getById(Long id) {
        //User user = userDao.selectByPrimaryKey(id);
        User user = userDao.selectById(id);
        log.info("用户账号：" + user.getAccount());
        log.info("用户姓名：" + user.getRealName());
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getByAccount(String account) {

        User user = userDao.selectByAccount(account);
        return user;
    }

    @Override
    public User add(User user) {

        User user1 = new User();
        user1.setTargetType(0);
        user1.setCorporationId(0L);
        user1.setPosition("CEO");
        user1.setAccount("durant");
        user1.setPassword("123456");
        user1.setRealName("kevin");
        user1.setIdentityNumber("1234567890");
        user1.setNickName("death god");
        user1.setEmail("durant@163.com");
        user1.setGender(0);
        user1.setCountry("USA");
        user1.setCity("new york");
        user1.setStatus(0);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        userDao.insert(user1);
        return user1;
    }

    @Override
    public List<User> getListAll() {
        return null;
    }
}
