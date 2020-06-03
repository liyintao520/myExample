package com.lyt.controller.file;

import com.lyt.module.file.entity.UploadFileResponse;
import com.lyt.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName FileController
 * @Description 上传下载文件测试类
 * @Author liyintao
 * @Date 2020/6/1 16:06
 */
@RestController
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * http://localhost:8010/uploadFile
     * 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("上传文件，file = {}", file);
        String fileName = fileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }


    /**
     * http://localhost:8010/uploadMultipleFiles
     * 上传多个文件
     * @param files
     * @return
     */
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        log.info("上传多个文件，files = {}", files);
        return Arrays.stream(files).map(this::uploadFile).collect(Collectors.toList());
    }

    /**
     * http://localhost:8010/downloadFile/1.gif
     * 下载文件
     * @param fileName
     * @param request
     * @return
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // 将文件作为资源加载
        Resource resource = fileService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            // 确定文件的内容类型
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("无法确定文件类型。");
        }

        // 如果类型为空，默认内容类型
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
