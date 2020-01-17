package com.example.core.service;

import com.example.core.entity.Solution;
import com.example.core.exception.ApplicationException;
import com.example.core.service.base.IBaseService;

import java.util.List;

public interface ISolutionService extends IBaseService<Long, Solution> {

    /**
     *
     * @param solutionIds
     * @return
     */
    List<Solution> getListByIds(List<Long> solutionIds) throws ApplicationException;
}
