package com.example.core.dao.base;

public interface IBaseDao<K, V> {

    V selectById(K id);

    int insert(V entity);

    int updateById(V entity);

    int deleteById(K id);
}
