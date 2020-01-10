package com.example.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 文件相关操作工具类
 * @author daniel
 * @date 2019-01-10
 */
@Component
@Slf4j
public class FileUtil {

    public boolean isFile(String filepath) {
        File f = new File(filepath);
        return f.exists() && f.isFile();
    }

    public boolean isDir(String dirPath) {
        File f = new File(dirPath);
        return f.exists() && f.isDirectory();
    }

    /**
     * 创建多级目录
     * @param path
     */
    public void makeDirs(String path) {
        File file = new File(path);
        // 如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }else {
            System.out.println("创建目录失败："+path);
        }
    }
}
