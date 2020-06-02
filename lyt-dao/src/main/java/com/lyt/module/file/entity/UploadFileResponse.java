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
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件下载的地址
     */
    private String fileDownloadUri;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小
     */
    private long size;

    /**
     * 构造器
     * @param fileName
     * @param fileDownloadUri
     * @param fileType
     * @param size
     */
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

}
