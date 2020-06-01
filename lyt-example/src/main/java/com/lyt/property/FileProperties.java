package com.lyt.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName FileProperties
 * @Description 读取配置参数
 * @Author liyintao
 * @Date 2020/6/1 17:47
 */
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {
    private String uploadDir;

//    public String getUploadDir() {
//        return uploadDir;
//    }
//    public void setUploadDir(String uploadDir) {
//        this.uploadDir = uploadDir;
//    }
}
