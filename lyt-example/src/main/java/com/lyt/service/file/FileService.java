package com.lyt.service.file;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/1 18:34
 */

import com.lyt.exception.FileException;
import com.lyt.property.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileService {
    // 文件在本地存储的地址
    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileProperties fileProperties) {
        // 文件地址赋值
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            // 创建目录
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // 获取原始文件名，并规范文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 检查文件名是否包含无效字符
            if (fileName.contains("..")) {
                log.error("对不起的！文件名包含无效的路径序列.");
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // 将文件复制到目标位置（用相同名称替换现有文件）
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
