package com.lyt.module.file.entity;

import lombok.Data;

/**
 * @ClassName UploadFileResponse
 * @Description 上传文件成功之后的Response响应实体类
 * @Author liyintao
 * @Date 2020/6/1 18:28
 */
@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

}
