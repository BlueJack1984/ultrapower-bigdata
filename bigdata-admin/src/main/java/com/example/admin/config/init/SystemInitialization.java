package com.example.admin.config.init;


import com.example.core.constants.StandardRepository;
import com.example.core.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 系统初始化执行，用来导入图片和文件等验证数据
 * @author daniel
 * @date 2019-01-15
 */
@Component
@Order(1)
@RequiredArgsConstructor
public class SystemInitialization implements ApplicationRunner {

    private final INewsService newsService;

    /**
     * 系统初始化执行的方法
     * @param args 携带的命令行参数
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Map<String, Object> fileRepository = StandardRepository.fileRepository;
        //这里填充标准数据，从数据库中获取
        //样本数据
        fileRepository.put("hello1", "world1");
        fileRepository.put("hello2", "world2");
        fileRepository.put("hello3", "world3");
        fileRepository.put("hello4", "world4");

    }
}
