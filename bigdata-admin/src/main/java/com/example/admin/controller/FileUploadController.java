package com.example.admin.controller;

import com.example.admin.dto.response.OutputResult;
import com.example.core.constants.ResponseCode;
import com.example.core.constants.StandardRepository;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传接口
 * @author daniel
 * @date 2019-12-30
 */
@Api(value = "file", tags = {"上传文件到oss的相关接口类"})
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
            log.error("【文件上传：图片上传接口-上传图片文件不存在】");
            return new OutputResult<>(ResponseCode.IMAGE_NOT_EXIST_ERROR);
        }
        String imageName = imageFile.getOriginalFilename();
        if (StringUtils.isEmpty(imageName)){
            log.error("【文件上传：图片上传接口-上传图片文件没有名称】");
            return new OutputResult<>(ResponseCode.IMAGE_NAME_NOT_EXIST_ERROR);
        }
        String contentType = imageFile.getContentType();
        if (null == contentType || ! contentType.contains(IMAGE_FLAG)) {
            log.error("【文件上传：图片上传接口-上传文件不是图片类型】");
            return new OutputResult<>(ResponseCode.IMAGE_TYPE_ERROR);
        }
        //拼接图片文件的存储路径
        StringBuilder url = new StringBuilder();
        url.append(IMAGE_FLAG)
            .append(SPLIT_PATH).append(path)
            .append(SPLIT_PATH).append(System.currentTimeMillis())
            .append(imageName.trim());
        //获取文件流
        BufferedImage sourceImage = null;
        try {
            sourceImage = ImageIO.read(imageFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【文件上传：图片上传接口-BufferedImage组件无法读取图片文件数据流】");
            return new OutputResult<>(ResponseCode.IMAGE_IO_READ_ERROR);
        }
        //校验上传图片尺寸大小
        Boolean checkResult = checkImageSize(sourceImage, targetType, checkFlag);
        if(false == checkResult) {
            log.error("【文件上传：图片上传接口-上传图片尺寸大小不符合要求】");
            return new OutputResult<>(ResponseCode.IMAGE_SIZE_CHECK_ERROR);
        }
        InputStream imageInputStream = null;
        try {
            imageInputStream = imageFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【文件上传：图片上传接口-无法打开图片文件数据流】");
            return new OutputResult<>(ResponseCode.IMAGE_IO_READ_ERROR);
        }
        //创建一个通用的多部分解析器
        String imageUrl = fileUploadService.uploadImage(imageInputStream, targetType, imageFile.getSize(), url.toString());
        return  new OutputResult<>(imageUrl);
    }

    /**
     * 上传图片模块
     * @param sourceImage 图片信息
     * @param targetType 图片所属类别，头像，名片，营业执照等
     * @param checkFlag 是否需要检查尺寸标志
     * @throws ApplicationException 上传异常
     */
    private Boolean checkImageSize(BufferedImage sourceImage, Integer targetType, Boolean checkFlag) throws ApplicationException{

        //判断是否需要检查尺寸
        if(false == checkFlag) {
            log.info("不需要检查图片尺寸！");
            return true;
        }
        //需要检查尺寸
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        //String key = String.valueOf(targetType) + (type == null ? "" : ("-" + String.valueOf(type)));
        String key = targetType.toString();
        Map<String, Object> fileRepository = StandardRepository.fileRepository;
        if(! fileRepository.containsKey(key)) {
            log.error("【文件上传：图片上传接口-上传图片尺寸大小不符合要求】");
            throw new ApplicationException(ResponseCode.IMAGE_SIZE_CHECK_ERROR);
        }
        Object standard = fileRepository.get(key);
//        if (mapTargetCheckImageSize.containsKey(key)) {
//            // 有该参数，进行校验
//            List<Integer> sizes = mapTargetCheckImageSize.get(key);
//            int w = sizes.get(0);
//            int h = sizes.get(1);
//            if (width != w && height != h) {
//
//                //throw new WrongOperationException("请确定尺寸，图片尺寸应为" + w + "*" + h);
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

        //判断上传文件是否存在
        MultipartFile documentFile = documentRequest.getFile(FILE_NAME);
        if(null == documentFile) {
            log.error("");
            return new OutputResult<>();
            //throw new ApplicationException(ApplicationException.PARAM_ERROR, "没有上传附件文件");
        }
        //根据传递参数判断文件类型
        String contentType = documentFile.getContentType();
        String fileName = documentFile.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)) {
            log.error("");
            return new OutputResult<>();
            //throw new ApplicationException(1, "上传文件名为空，请设置上传文件名称");
        }
        StringBuilder url = new StringBuilder();
        //判断上传文件是否为正确的类型，通过请求头的contentType判断
        if(contentType == null || ! (contentType.contains(WORD_FLAG) || contentType.contains(PDF_FLAG) ||
                (contentType.contains("octet-stream") && ".docx".equals(fileName.substring(fileName.indexOf(".")))))) {
            log.error("");
            return new OutputResult<>();
            //throw new ApplicationException(ApplicationException.PARAM_ERROR, "附件请求contentType参数错误,只能是word或pdf文档");
        }
        if(contentType.contains(WORD_FLAG) || contentType.contains("octet-stream")) {
            url.append(WORD_FLAG);
        }else {
            url.append(PDF_FLAG);
        }
        url.append(SPLIT_PATH).append(path).append(SPLIT_PATH).append(System.currentTimeMillis()).append(fileName.trim());
        //检验文件大小
        if(checkFlag) {
            //checkFileSize(file);
        }
        InputStream documentInputStream = null;
        try {
            documentInputStream = documentFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("oss上传文件异常：{}", e.getMessage());
            return new OutputResult<>();
            //throw new AlibabaOssException("上传文件异常");
        }

//        InputStream inputStream = null;
//        try {
//            inputStream = file.getInputStream();
//        } catch (IOException e) {
//            log.error("oss上传文件异常：{}", e.getMessage());
//            throw new AlibabaOssException("上传文件异常");
//        }
//        String fileUrl = fileStorageServer.storage(inputStream, file.getSize(), url.toString());
        //创建一个通用的多部分解析器
        String documentUrl = fileUploadService.uploadDocument(documentInputStream, targetType, documentFile.getSize(), url.toString());
        return  new OutputResult<>(documentUrl);
    }

        /**
     * @author lushusheng 2018-10-09
     * 上传文件到oss,包含word，pdf两种文件格式
     * @param documentFile 需要检查大小的文件
     * @return 无
     * @throws ApplicationException 上传文件产生的异常
     */
    private Boolean checkFileSize(MultipartFile documentFile)throws ApplicationException {
        //获取文件的字节大小，单位byte
        long size = documentFile.getSize();
//        if(size > FILE_MAX_SIZE) {
//            throw new ApplicationException(1, "上传文件大小超出最大限制，请重新上传文件");
//        }
        return true;
    }
    /**
     * @param documents
     * @return
     */
    @PostMapping("/documents")
    public OutputResult<Void> uploadDocument(@RequestParam("documents") MultipartFile[] documents) {

        for(MultipartFile documentFile : documents) {

        }
        System.out.println("上传文件夹...");
        File file;
        String fileName="";
        String filePath="";
        for (MultipartFile f : documents) {
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
     * @param images
     * @return
     */
    @PostMapping("/images")
    public OutputResult<List<String>> uploadImage(@RequestParam("images") MultipartFile[] images) {

        List<String> imageUrlList = new ArrayList<>();
        for(MultipartFile imageFile : images) {
            //
        }
        return new OutputResult<>(imageUrlList);
    }
}
