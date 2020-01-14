package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IFileUploadService;
import com.example.core.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
    private final IFileUploadService fileUploadService;

    private static final String FILE_IMAGE = "imagefile";
    private static final String IMAGE_FLAG = "image";
    private static final String SPLIT_SIZE = "-";
    private static final String SPLIT_PATH = "/";

    private static final String FILE_NAME = "fileName";
    private static final String WORD_FLAG = "word";
    private static final String PDF_FLAG = "pdf";
    private static final Integer RESOURCE_TYPE_DOCUMENT = 3;

    //@Value("${file.image.name}")
    private final String FILE_IMAGE_NAME = null;
    //@Value("${file.document.name}")
    private final String FILE_DOCUMENT_NAME = null;

    /**
     * 上传图片模块
     * @param imageRequest 请求头包含图片信息
     * @param path 上传oss的文件夹
     * @param targetType 实体类型
     * @param checkFlag 是否要检查上传文件尺寸
     * @throws ApplicationException 上传异常
     */
    @ApiOperation(value = "上传并审核图片", notes = "图片上传至oss")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "request", value = "请求头包含图片信息", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "targetType", value = "实体类型", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "checkFlag", value = "是否要检查上传文件尺寸")
    })
    @CrossOrigin
    @PostMapping(value ="/image")
    public OutputResult<String> uploadImage(MultipartHttpServletRequest imageRequest,
                @RequestParam(value = "path", required = true) String path,
                @RequestParam(value = "targetType", required = true) Integer targetType,
                @RequestParam(value = "checkFlag", required = false, defaultValue = "true") Boolean checkFlag) throws ApplicationException {


        MultipartFile imageFile = imageRequest.getFile(FILE_IMAGE_NAME);
        if (null == imageFile){
            log.error("");
            return new OutputResult<>();
            //throw new WrongOperationException("请上传图片");
        }
        String imageName = imageFile.getOriginalFilename();
        if (StringUtils.isEmpty(imageName)){
            log.error("");
            return new OutputResult<>();
            //throw new WrongOperationException("请设置图片的文件名称");
        }
        String contentType = imageFile.getContentType();
        if (null == contentType || ! contentType.contains(IMAGE_FLAG)) {
            log.error("");
            return new OutputResult<>();
            //throw new WrongOperationException("请上传图片");
        }
        StringBuilder url = new StringBuilder();
        url.append(IMAGE_FLAG)
            .append(SPLIT_PATH).append(path)
            .append(SPLIT_PATH).append(System.currentTimeMillis())
            .append(imageName.trim());
        BufferedImage sourceImg = null;
        try {
            sourceImg = ImageIO.read(imageFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
            return new OutputResult<>();
        }
        Boolean checkResult = check(sourceImg, checkFlag);
        if(false == checkResult) {
            log.error("");
            return new OutputResult<>();
        }
        InputStream imageInputStream = null;
        try {
            imageInputStream = imageFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
            return new OutputResult<>();
        }

        //创建一个通用的多部分解析器
        String imageUrl = fileUploadService.uploadImage(imageInputStream, targetType, imageFile.getSize(), url.toString());
        return  new OutputResult<>(imageUrl);
    }

    /**
     * 上传图片模块
     * @param sourceImg 请求头包含图片信息
     * @param checkFlag 上传oss的文件夹
     * @throws ApplicationException 上传异常
     */
    private Boolean check(BufferedImage sourceImg, Boolean checkFlag) {

//        if (checkFlag) {
//            int width = sourceImg.getWidth();
//            int height = sourceImg.getHeight();
//            String key = String.valueOf(targetType) + (type == null ? "" : ("-" + String.valueOf(type)));
//            if (mapTargetCheckImageSize.containsKey(key)) {
//                // 有该参数，进行校验
//                List<Integer> sizes = mapTargetCheckImageSize.get(key);
//                int w = sizes.get(0);
//                int h = sizes.get(1);
//                if (width != w && height != h) {
//                    throw new WrongOperationException("请确定尺寸，图片尺寸应为" + w + "*" + h);
//                }
//            }
//        }
        return true;
    }

    /**
     * 上传文件,包含word，pdf两种文件格式
     * @param documentRequest 对应不同格式文件的请求
     * @param path 文件的存储路径
     * @param targetType 实体类型
     * @param checkFlag 是否校验文件的大小
     * @return 文件资源对象
     * @throws ApplicationException 上传文件产生的异常
     */
    @ApiOperation(value = "上传word或者pdf文件", notes = "文件上传至oss")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "HttpServletRequest", name = "fileRequest", value = "请求头包含文件信息", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "path", value = "上传oss的文件夹", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "targetType", value = "实体类型", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "boolean", name = "checkFlag", value = "判断是否检查文件大小")
    })
    @CrossOrigin
    @PostMapping(value ="/document")
    public OutputResult<String> uploadDocument(MultipartHttpServletRequest documentRequest,
                 @RequestParam(value = "path", required = true) String path,
                 @RequestParam(value = "targetType", required = true) Integer targetType,
                 @RequestParam(value = "checkFlag", required = false, defaultValue = "true") Boolean checkFlag) throws ApplicationException {

        MultipartFile documentFile = documentRequest.getFile("");
        InputStream documentInputStream = null;
        try {
            documentInputStream = documentFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建一个通用的多部分解析器
        String url = fileUploadService.uploadDocument(documentInputStream, path, targetType, checkFlag);
        return  new OutputResult<>(url);
    }
    /**
     * @param dir
     * @return
     */
    @PostMapping("/documents")
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
     * @param dir
     * @return
     */
    @PostMapping("/images")
    public OutputResult<Void> uploadImage(@RequestParam("dir") MultipartFile[] dir) {
        return new OutputResult<>();
    }
}
