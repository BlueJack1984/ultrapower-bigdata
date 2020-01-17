package com.example.core.service;

import com.example.core.entity.Solution;
import com.example.core.exception.ApplicationException;
import com.example.core.service.base.IBaseService;

import java.util.List;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
public interface ISolutionService extends IBaseService<Long, Solution> {

    /**
     *
     * @param solutionIds
     * @return
     */
    List<Solution> getListByIds(List<Long> solutionIds) throws ApplicationException;
}
