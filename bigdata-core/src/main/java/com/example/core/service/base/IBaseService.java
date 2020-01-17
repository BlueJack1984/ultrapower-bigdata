package com.example.core.service.base;


import com.example.core.exception.ApplicationException;

/**
 * 基础服务
 * @author daniel
 * @date 2020-01-13
 * @param <K> 表示主键类型，如Long型主键id
 * @param <V> 表示实体类型，如User
 */
public interface IBaseService<K, V> {

    /**
     * 根据id获取实体信息
     * @param id 实体id
     * @return 返回实体信息
     */
    V getById(K id);

    /**
     * 根据id更新实体信息
     * @param entity 实体信息，其中包含id
     * @return 无返回
     */
    void modifyById(V entity);

    /**
     * 新增实体信息
     * @param entity 实体信息，可能包括id
     * @return 无返回
     */
     void add(V entity) throws ApplicationException;
}
