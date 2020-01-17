package com.example.core.service.impl;

import com.example.core.dao.IUserDao;
import com.example.core.entity.Dictionary;
import com.example.core.entity.News;
import com.example.core.entity.User;
import com.example.core.service.IDictionaryService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DictionaryServiceImpl implements IDictionaryService {

    private final IUserDao userDao;


    @Override
    public Dictionary getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Dictionary entity) {

    }

    @Override
    public void add(Dictionary entity) {

        int i = 10;
        System.out.println("heloo word");
    }
}
