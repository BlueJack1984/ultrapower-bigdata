package com.example.core.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.diich.exp.third.AlibabaOssException;
import com.diich.helper.storage.IFileStorageService;
import com.diich.exp.ApplicationException;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * oss的文件存储服务具体实现
 * @author wujr 2018-09-05
 */
@Service("ossFileStorageServer")
@Slf4j
public class OssFileUploadServiceImpl implements IFileUploadService {
    @Value("${OSS.accessKeyId}")
    private  String accessKeyId ;
    @Value("${OSS.accessKeySecret}")
    private  String accessKeySecret;
    @Value("${OSS.Server.Path}")
    private  String serverPath;
    @Value("${OSS.bucketName}")
    private  String bucketName;
    @Value("${OSS.upfile.diich.resource}")
    private String fileUploadUrl;

    /**
     * 把流内容存储到oss上
     * @param inputStream 流内容
     * @param size 文件的长度
     * @param path 文件存储路径
     * @throws ApplicationException 存储异常
     */
    @Override
    public String storage(InputStream inputStream, long size, String path) throws ApplicationException {
        OSSClient client = new OSSClient(serverPath, accessKeyId , accessKeySecret);

        String key = fileUploadUrl + "/" + path;
        try {
            ByteArrayInputStream content = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();

            // 必须设置ContentLength
            meta.setContentLength(size);

            // 上传Object.
            client.putObject(bucketName, path, content, meta);
            return key;
        }catch (Exception e){
            log.error("oss文件上传异常：{}", e.getMessage());
            throw new AlibabaOssException("文件上传异常");
        }
    }

    /**
     * 从oss中删除文件
     * @param path 文件路径
     * @throws ApplicationException 删除异常
     */
    @Override
    public void delete(String path) throws ApplicationException{
        OSSClient ossClient = new OSSClient(serverPath, accessKeyId, accessKeySecret);
        try {
            ObjectListing ObjectListing = ossClient.listObjects(bucketName);
            List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
            log.debug("删除汇总：{}", listDeletes.size());
            ossClient.deleteObject(bucketName, path);
        }catch (Exception e){
            log.error("oss文件删除异常：{}", e.getMessage());
            throw new AlibabaOssException("文件删除异常");
        }
    }

    @Override
    public String uploadImage(InputStream imageInputStream, Integer targetType, Long imageSize, String url) throws ApplicationException {

        return null;
    }
}