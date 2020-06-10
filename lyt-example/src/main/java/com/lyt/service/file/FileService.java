package com.lyt.service.file;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/1 18:34
 */

import com.lyt.exception.FileException;
import com.lyt.module.file.entity.UploadFileResponse;
import com.lyt.property.FileProperties;
import com.lyt.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
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
    public static void main(String[] args) throws Exception {
        try {
            String filePath = "D:\\excel\\蛋蛋老骚逼.xlsx";
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            // TODO MultipartFile 与 File 的 互相转换 使用【MockMultipartFile】 在spring-test包中
            MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
            System.out.println(multipartFile.getName());

            storeFileTest(multipartFile);

            String fileName = "测试main方法";
            String fileDownloadUri = "D:/uploads/蛋蛋老骚逼.xlsx";
            UploadFileResponse result = new UploadFileResponse(fileName, fileDownloadUri, multipartFile.getContentType(), multipartFile.getSize());
            System.err.println("成功" + JsonUtil.toJson(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String storeFileTest(MultipartFile file) {
        // 获取原始文件名，并规范文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 检查文件名是否包含无效字符
            if (fileName.contains("..")) {
                log.error("对不起的！文件名包含无效的路径序列.");
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // 将文件复制到目标位置（用相同名称替换现有文件）
//            Path targetLocation = "D:/uploads/蛋蛋老骚逼.xlsx";
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:/uploads/蛋蛋老骚逼.xlsx"));
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
