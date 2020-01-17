package com.example.core.service.impl;

import com.example.core.dao.ISolutionDao;
import com.example.core.dao.IUserDao;
import com.example.core.entity.News;
import com.example.core.entity.Solution;
import com.example.core.entity.User;
import com.example.core.service.ISolutionService;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SolutionServiceImpl implements ISolutionService {

    private final ISolutionDao solutionDao;


    @Override
    public Solution getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(Solution entity) {

    }

    @Override
    public void add(Solution entity) {

    }


    @Override
    public List<Solution> getListByIds(List<Long> solutionIds) {

        List<Solution> solutionList = solutionDao.selectListByIds(solutionIds);
        return solutionList;
    }
}
