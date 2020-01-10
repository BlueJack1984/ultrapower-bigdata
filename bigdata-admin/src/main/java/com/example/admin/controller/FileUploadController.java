package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.utils.FileUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file/upload")
public class FileUploadController {

    private final FileUtil fileUtil;
    /**
     *
     * @param dir
     * @return
     */
    @PostMapping("/document")
    public OutputResult<Void> uploadDocument(@RequestParam("dir") MultipartFile[] dir) {
        System.out.println("上传文件夹...");
        File file;
        String fileName="";
        String filePath="";
        for (MultipartFile f : dir) {
            fileName=f.getOriginalFilename();
            String type=f.getContentType();
            System.out.println("\n"+fileName+" ,"+type);
            filePath="D:\\upload\\"+fileName.substring(0,fileName.lastIndexOf("/"));
            if(!fileUtil.isDir(filePath)){
                fileUtil.makeDirs(filePath);
            }
            file = new File("D:\\upload\\" + fileName);
            try {
                file.createNewFile();
                //将上传文件保存到一个目标文件中
                f.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return "filelist";
        return new OutputResult<>();
    }

    /**
     *
     * @param dir
     * @return
     */
    @PostMapping("/image")
    public OutputResult<Void> uploadImage(@RequestParam("dir") MultipartFile[] dir) {
        return new OutputResult<>();
    }
}
