package com.example.admin.config.fileupload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 上传文件相关配置
 * @author daniel
 * @date 2019-01-10
 */
@Configuration
public class FileUploadConfig {

    /**
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大   KB,MB
        //factory.setMaxFileSize("100MB");
        //factory.set
        /// 设置总上传数据总大小
        //factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }
}
