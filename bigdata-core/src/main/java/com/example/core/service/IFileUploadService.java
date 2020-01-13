package com.example.core.service;

import com.example.core.exception.ApplicationException;

/**
 * 文件上传服务接口定义
 * @author daniel 2020-01-11
 */
public interface IFileUploadService {
    /**
     * 上传图片文件
     * @param imageRequest 图片文件的请求
     * @param path 图片的存储路径
     * @param type 图片对应的实体的子类型
     * @param targetType 图片所属的实体类型
     * @param checkSize 是否校验图片的尺寸
     * @return 图片资源对象
     * @throws ApplicationException 上传图片异常
     */
//    ResourcePO uploadImage(MultipartHttpServletRequest imageRequest, String path,
//                           Integer type, int targetType,
//                           boolean checkSize) throws ApplicationException;

    /**
     * @author daniel
     * 上传文件,包含word，pdf两种文件格式
     * @param fileRequest 对应不同格式文件的请求
     * @param path 文件的存储路径
     * @param checkSize 是否校验文件的大小
     * @return 文件资源对象
     * @throws ApplicationException 上传文件产生的异常
     */
//    ResourcePO uploadWordAndPDF(MultipartHttpServletRequest fileRequest, String path,
//                                boolean checkSize) throws ApplicationException;

}