package com.example.core.dao.base;

/**
 * 基础dao服务模块
 * @author daniel
 * @date 2019-01-17
 * @param <K> 主键id的类型
 * @param <V> 实体类型
 */
public interface IBaseDao<K, V> {

    /**
     * 根据id查询实体信息
     * @param id 实体id
     * @return 返回查询到的实体信息
     */
    V selectById(K id);
    /**
     * 新增插入一条实体信息
     * @param entity 实体信息
     * @return 返回操作的行数
     */
    int insert(V entity);
    /**
     * 修改已有实体信息
     * @param entity 实体信息
     * @return 返回操作的行数
     */
    int updateById(V entity);
    /**
     * 根据id逻辑删除一条实体信息
     * @param id 实体id
     * @return 返回操作的行数
     */
    int deleteById(K id);
}
