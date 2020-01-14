package com.example.core.service.impl;


import com.example.core.exception.ApplicationException;
import com.example.core.service.IFileStorageService;
import com.example.core.service.IFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传的业务逻辑具体实现
 * @author daniel 2020-01-12
 * @date 2019-01-14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements IFileUploadService {

    private final IFileStorageService ossFileStorageService;

    private static final String FILE_IMAGE = "imagefile";
    private static final String IMAGE_FLAG = "image";
    private static final String SPLIT_SIZE = "-";
    private static final String SPLIT_PATH = "/";

    private static final String FILE_NAME = "fileName";
    private static final String WORD_FLAG = "word";
    private static final String PDF_FLAG = "pdf";
    private static final Integer RESOURCE_TYPE_DOCUMENT = 3;
    //定义为10M大小字节数（bytes）
    private static final long FILE_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 对需要校验的图片的尺寸进行定义
     * @author daniel
     * @date 2019-01-13
     */
    @Value("#{${image.upload.size:{\"5-0\":\"224-138\",\"5-1\":\"649-320\"}}}")
    private Map<String, String> mapImageSize;
    private Map<String, List<Integer>> mapTargetCheckImageSize = new HashMap<>();

//    private final IFileStorageService fileStorageServer;
//    private final IContentReviewService contentReviewServer;
//    private final IResourceDao resourceDao;
//
//    @PostConstruct
//    public void onInitialize(){
//        if (mapImageSize != null && !mapImageSize.isEmpty()){
//            for (String key : mapImageSize.keySet()){
//                String value = mapImageSize.get(key);
//                if (StringUtils.isEmpty(value)){
//                    continue;
//                }
//                String[] sizes = value.split(SPLIT_SIZE);
//                if (sizes.length != 2){
//                    log.error("图片的校验尺寸配置错误");
//                    continue;
//                }
//                List<Integer> imageSize = new ArrayList<>();
//                imageSize.add(Integer.valueOf(sizes[0]));
//                imageSize.add(Integer.valueOf(sizes[1]));
//                mapTargetCheckImageSize.put(key, imageSize);
//            }
//        }
//    }
//
//    /**
//     * 上传图片到oss，并且审核通过后，保存到资源数据表中
//     * @param imageRequest 图片文件的请求
//     * @param path 图片的存储路径
//     * @param type 图片对应的实体的子类型
//     * @param targetType 图片所属的实体类型
//     * @param checkSize 是否校验图片的尺寸
//     * @return 上传成功后的资源对象
//     * @throws ApplicationException 上传保存异常
//     */
//    @Override
//    public ResourcePO uploadImage(MultipartHttpServletRequest imageRequest, String path,
//                                  Integer type, int targetType,
//                                  boolean checkSize) throws ApplicationException {
//        MultipartFile imageFile = imageRequest.getFile(FILE_IMAGE);
//        if (imageFile == null){
//            throw new WrongOperationException("请上传图片");
//        }
//        String fileName = imageFile.getOriginalFilename();
//        if (StringUtils.isEmpty(fileName)){
//            throw new WrongOperationException("请设置图片的文件名称");
//        }
//        String fileUrl;
//        try {
//            StringBuilder url = new StringBuilder();
//            String contentType = imageFile.getContentType();
//            if (contentType == null || !contentType.contains(IMAGE_FLAG)) {
//                throw new WrongOperationException("请上传图片");
//            }
//            url.append(IMAGE_FLAG)
//                    .append(SPLIT_PATH).append(path)
//                    .append(SPLIT_PATH).append(System.currentTimeMillis())
//                    .append(fileName.trim());
//
//            BufferedImage sourceImg = ImageIO.read(imageFile.getInputStream());
//
//            if (checkSize) {
//                int width = sourceImg.getWidth();
//                int height = sourceImg.getHeight();
//                String key = String.valueOf(targetType) + (type == null ? "" : ("-" + String.valueOf(type)));
//                if (mapTargetCheckImageSize.containsKey(key)) {
//                    // 有该参数，进行校验
//                    List<Integer> sizes = mapTargetCheckImageSize.get(key);
//                    int w = sizes.get(0);
//                    int h = sizes.get(1);
//                    if (width != w && height != h) {
//                        throw new WrongOperationException("请确定尺寸，图片尺寸应为" + w + "*" + h);
//                    }
//                }
//            }
//            InputStream inputStream = imageFile.getInputStream();
//            fileUrl = fileStorageServer.storage(inputStream, imageFile.getSize(), url.toString());
//
//            // 对图片进行审核
//            List<ReviewTask> tasks = new ArrayList<>();
//            ReviewTask task = ReviewTask.builder().dataId(UUID.randomUUID().toString())
//                    .url(fileUrl)
//                    .time(new Date()).build();
//            tasks.add(task);
//            ReviewOutputDTO output = contentReviewServer.imageReview(tasks);
//            if (output.getSign() == ReviewConst.NOPASS) {
//                try {
//                    fileStorageServer.delete(url.toString());
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                }
//                throw new WrongOperationException("图片包含敏感信息");
//            }
//        }catch (ApplicationException e){
//            throw e;
//        }catch (Exception e){
//            log.error("oss上传文件异常：{}", e.getMessage());
//            throw new AlibabaOssException("上传图片异常");
//        }
//        //构造资源并存入数据库
//        ResourcePO resource =new ResourcePO();
//        resource.setId(IdWorker.getId());
//        resource.setUri(fileUrl);
//        resource.setType(0);
//        resource.setStatus(0);
//        resourceDao.insert(resource);
//
//        return resource;
//    }
//
//    /**
//     * @author daniel 2020-01-12
//     * 上传文件到oss,包含word，pdf两种文件格式
//     * @param fileRequest 对应不同格式文件的请求
//     * @param path 文件的存储路径
//     * @param checkSize 是否校验文件的大小
//     * @return 文件资源对象
//     * @throws ApplicationException 上传文件产生的异常
//     * .doc	application/msword
//     * .dot	application/msword
//     * .docx application/vnd.openxmlformats-officedocument.wordprocessingml.document
//     * 对于windows系统和mac系统，.docx格式的word文件对应contentType值不一致
//     * mac系统下contentType：application/vnd.openxmlformats-officedocument.wordprocessingml.document
//     * windows系统下contentType：application/octet-stream
//     */
//    @Override
//    public ResourcePO uploadWordAndPDF(MultipartHttpServletRequest fileRequest, String path,
//                                       boolean checkSize) throws ApplicationException {
//        //判断上传文件是否存在
//        MultipartFile file = fileRequest.getFile(FILE_NAME);
//        if(file == null) {
//            throw new ApplicationException(ApplicationException.PARAM_ERROR, "没有上传附件文件");
//        }
//        //根据传递参数判断文件类型
//        String contentType = file.getContentType();
//        String fileName = file.getOriginalFilename();
//        if(StringUtils.isEmpty(fileName)) {
//            throw new ApplicationException(1, "上传文件名为空，请设置上传文件名称");
//        }
//        StringBuilder url = new StringBuilder();
//        //判断上传文件是否为正确的类型，通过请求头的contentType判断
//        if(contentType == null || ! (contentType.contains(WORD_FLAG) || contentType.contains(PDF_FLAG) ||
//                (contentType.contains("octet-stream") && ".docx".equals(fileName.substring(fileName.indexOf(".")))))) {
//            throw new ApplicationException(ApplicationException.PARAM_ERROR, "附件请求contentType参数错误,只能是word或pdf文档");
//        }
//        if(contentType.contains(WORD_FLAG) || contentType.contains("octet-stream")) {
//            url.append(WORD_FLAG);
//        }else {
//            url.append(PDF_FLAG);
//        }
//        url.append(SPLIT_PATH).append(path).append(SPLIT_PATH).append(System.currentTimeMillis()).append(fileName.trim());
//        //检验文件大小
//        if(checkSize) {
//            checkFileSize(file);
//        }
//        InputStream inputStream = null;
//        try {
//            inputStream = file.getInputStream();
//        } catch (IOException e) {
//            log.error("oss上传文件异常：{}", e.getMessage());
//            throw new AlibabaOssException("上传文件异常");
//        }
//        String fileUrl = fileStorageServer.storage(inputStream, file.getSize(), url.toString());
//
//        //构造资源并存入数据库
//        ResourcePO resource =new ResourcePO();
//        resource.setId(IdWorker.getId());
//        resource.setUri(fileUrl);
//        resource.setDescription(fileName);
//        //文档的type值为3
//        resource.setType(RESOURCE_TYPE_DOCUMENT);
//        resource.setStatus(0);
//        resourceDao.insert(resource);
//        return resource;
//    }
//
//    /**
//     * @author lushusheng 2018-10-09
//     * 上传文件到oss,包含word，pdf两种文件格式
//     * @param file 需要检查大小的文件
//     * @return 无
//     * @throws ApplicationException 上传文件产生的异常
//     */
//    private void checkFileSize(MultipartFile file)throws ApplicationException {
//        //获取文件的字节大小，单位byte
//        long size = file.getSize();
//        if(size > FILE_MAX_SIZE) {
//            throw new ApplicationException(1, "上传文件大小超出最大限制，请重新上传文件");
//        }
//    }
    /**
     * TODO
     * @author lushusheng 2018-10-09
     * 上传文件到oss,包含word，pdf两种文件格式
     * @param
     * @param
     * @param
     * @return
     * @throws ApplicationException 上传文件产生的异常
     */
    private void checkFileSensitive()throws ApplicationException {
        // 对word和pdf类型文档内容的敏感信息进行审核
    }

    @Override
    public String uploadImage(InputStream imageInputStream, Integer targetType, Long imageSize, String url) throws ApplicationException {

        String uploadUrl = ossFileStorageService.storage(imageInputStream, imageSize, url);
        return uploadUrl;
    }

    @Override
    public String uploadDocument(InputStream documentInputStream, String path,  Integer targetType, boolean checkSize) throws ApplicationException {
        return null;
    }
}



