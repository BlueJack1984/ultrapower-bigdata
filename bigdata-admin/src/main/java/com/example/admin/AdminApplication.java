package com.example.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描实例
@SpringBootApplication(scanBasePackages = {"com.example.admin", "com.example.core.service"})
//扫描dao接口
@MapperScan(basePackages = {"com.example.core.dao"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
