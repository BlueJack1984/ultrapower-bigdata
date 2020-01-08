package com.example.core.dao;

import com.example.core.dao.base.IBaseDao;
import com.example.core.entity.News;
import com.example.core.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IProductDao extends IBaseDao<Long, Product> {

}
