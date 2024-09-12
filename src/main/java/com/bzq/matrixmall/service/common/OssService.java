package com.bzq.matrixmall.service.common;

import com.bzq.matrixmall.model.dto.common.FileInfo;
import org.springframework.web.multipart.MultipartFile;

//对象存储服务接口层
public interface OssService {
    //上传文件
    FileInfo uploadFile(MultipartFile file);

    //删除文件
    boolean deleteFile(String filePath);
}
