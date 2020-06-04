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

    /**
     * 上传文件的路径地址，配置文件中查看file开头的属性：  upload-dir
     */
    private String uploadDir;

}
