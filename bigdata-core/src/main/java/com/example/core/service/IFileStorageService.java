package com.example.core.service;

import com.example.core.exception.ApplicationException;

import java.io.InputStream;

/**
 * 文件存储服务接口定义
 * @author wujr 2018-09-05
 */
public interface IFileStorageService {
    /**
     * 存储流内容到文件
     * @param inputStream 流内容
     * @param size 文件的长度
     * @param path 文件存储路径
     * @return 存储后的文件路径
     * @throws ApplicationException 存储异常
     */
    String storage(InputStream inputStream, long size, String path) throws ApplicationException;

    /**
     * 删除文件
     * @param path 文件路径
     * @throws ApplicationException 删除异常
     */
    void delete(String path) throws ApplicationException;
}
