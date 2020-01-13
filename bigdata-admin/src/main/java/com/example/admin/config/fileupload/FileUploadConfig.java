package com.example.admin.config.fileupload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 上传文件相关配置
 * @author daniel
 * @date 2019-01-10
 */
@Configuration
public class FileUploadConfig {

    /**
     * 设置上传文件的大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大100 * 1024KB,100MB
        factory.setMaxFileSize(DataSize.ofKilobytes(100 * 1024));
        /// 设置总上传数据总大小500 * 1024KB,500MB
        factory.setMaxRequestSize(DataSize.ofKilobytes(500 * 1024));
        return factory.createMultipartConfig();
    }
}
