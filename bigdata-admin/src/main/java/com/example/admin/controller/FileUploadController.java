package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.exception.ApplicationException;
import com.example.core.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传接口
 * @author daniel
 * @date 2019-12-30
 */
@Api(value = "file", description = "上传文件到oss的相关接口类")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file/upload")
public class FileUploadController {

    private final FileUtil fileUtil;
    //private final IFileUploadService fileUploadServer;

    /**
     * @author daniel
     * @throws ApplicationException 上传异常
     */
    @PostMapping(value ="/image")
    @ApiOperation(value = "上传并审核图片", notes = "图片上传至oss")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "request", value = "请求头包含图片信息", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "targetType", value = "实体类型", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "type", value = "实体子类型")
    })
    @CrossOrigin
    public OutputResult<Void> upImage(MultipartHttpServletRequest imgRequest,
                                            @RequestParam(value = "type",required = false,defaultValue = "")Integer type,
                                            @RequestParam("targetType") int targetType,
                                            @RequestParam("path") String path,
                                            @RequestParam(value = "checkSize", defaultValue = "true", required = false) boolean checkSize) throws ApplicationException {
        //创建一个通用的多部分解析器
        //ResourcePO resource = fileUploadServer.uploadImage(imgRequest, path, type, targetType, checkSize);

        return  new OutputResult<>();
    }

    /**
     * @author lushusheng 2018-10-09
     * 上传文件,包含word，pdf两种文件格式
     * @param fileRequest 对应不同格式文件的请求
     * @param path 文件的存储路径
     * @param checkSize 是否校验文件的大小
     * @return 文件资源对象
     * @throws ApplicationException 上传文件产生的异常
     */
    @RequestMapping(value ="word_pdf/up",method = RequestMethod.POST)
    @ApiOperation(value = "上传word或者pdf文件", notes = "文件上传至oss")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "fileRequest", value = "请求头包含文件信息", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "boolean", name = "checkSize", value = "判断是否检查文件大小")
    })
    @CrossOrigin
    public OutputResult<Void> uploadWordAndPDF(MultipartHttpServletRequest fileRequest,
                                                     @RequestParam("path") String path,
                                                     @RequestParam(value = "checkSize", defaultValue = "true", required = false) boolean checkSize) throws ApplicationException {
        //创建一个通用的多部分解析器
        //ResourcePO resource = fileUploadServer.uploadWordAndPDF(fileRequest, path, checkSize);
        return  new OutputResult<>();
    }
    /**
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
    @PostMapping("/images")
    public OutputResult<Void> uploadImage(@RequestParam("dir") MultipartFile[] dir) {
        return new OutputResult<>();
    }
}
