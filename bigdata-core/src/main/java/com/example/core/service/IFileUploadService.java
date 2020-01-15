package com.example.core.service;

import com.example.core.exception.ApplicationException;

import java.io.InputStream;

/**
 * 文件上传服务接口定义
 * @author daniel
 * @date 2020-01-11
 */
public interface IFileUploadService {
    /**
     * 上传图片文件
     * @param imageInputStream 图片数据流
     * @param targetType 图片所属类别，如头像、名片、营业执照等
     * @param imageSize 图片尺寸
     * @param url 图片存储路径（部分目录）
     * @return 图片资源访问地址
     * @throws ApplicationException 上传图片异常
     */
    String uploadImage(InputStream imageInputStream, Integer targetType, Long imageSize, String url) throws ApplicationException;

    /**
     * 上传文件,包含word，pdf两种文件格式
     * @param documentInputStream 文件数据流
     * @param targetType 文件所属类别，如政策、资讯等
     * @param documentSize 文件大小
     * @param url 文件存储路径（部分目录）
     * @return 文件资源访问地址
     * @throws ApplicationException 上传文件产生的异常
     */
    String uploadDocument(InputStream documentInputStream, Integer targetType, Long documentSize, String url) throws ApplicationException;

}