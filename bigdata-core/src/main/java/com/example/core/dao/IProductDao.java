package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.News;
import com.example.core.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IProductDao extends IBaseDao<Long, Product> {

    /**
     *
     * @param productIds
     * @return
     */
    List<Product> selectListByIds(@Param("productIds") List<Long> productIds);


    List<Product> getListByConditionPage(@Param("keyword") String keyword);
}
