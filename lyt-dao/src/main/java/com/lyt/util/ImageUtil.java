package com.lyt.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;

@Slf4j
public class ImageUtil {
    public static void main(String[] args) throws Exception {
//        String[] width = new String[]{"1920", "1080", "1280"};
//        long start = System.currentTimeMillis();
//        compressImage("D:/Develope/java/workspaces/ai/ai-qas/src/test/resources/tmp/吉林省支持创新创业政策_02.png",
//                "D:/Develope/java/workspaces/ai/ai-qas/src/test/resources/new",
//                "吉林省支持创新创业政策_04.png", width
//        );
//        compressImageNoCopy("D:/Develope/java/workspaces/ai/ai-qas/src/test/resources/tmp/默认图.png",width);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);

//        String filePath = getFilePath("001", "001");
//        System.out.println(filePath);
//        filePath = getFilePath("001", "001", "http://192.168.10.239:81/file/image/{width}/a.jpg");
//        System.out.println(filePath);
//        filePath = getFilePath("001", "100", "http://192.168.10.239:81/file/image/{width}/a.jpg");
//        System.out.println(filePath);
//
//        String[] files = new String[]{"D:/IDEA/resin-4.0.63/webapps/pcfile/image/case/jsp/201912301577689364559052842.jpg", "D:/IDEA/resin-4.0.63/webapps/pcfile/image/case/jsp/201912301577689488205075279.jpg"};
//        System.out.println(copyFiles(files, "D:/IDEA/resin-4.0.63/webapps/pcfile/image/case/2/jsp"));
        System.out.println(deleteFiles("D:/IDEA/resin-4.0.63/webapps/pcfile/image/case/2"));
    }
    /**
     * 复制文件到指定目录
     *
     * @param files        文件全路径数组，包含文件名称
     * @param destFilePath 目标路径
     * @return
     */
    public static boolean copyFiles(String[] files, String destFilePath) {
       log.info("copyFiles :: files:: " + Arrays.toString(files) + " destFilePath::" + destFilePath);
        boolean success = false;
        try {
            File tmp1 = null;
            File tmp2 = new File(destFilePath);
            if (!tmp2.exists()) {
                tmp2.mkdirs();
            }
            for (String fileNmae : files
            ) {
                tmp1 = new File(fileNmae);
                tmp2 = new File(destFilePath + "/" + tmp1.getName());
                FileUtils.copyFile(tmp1, tmp2);
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 删除图片
     *
     * @param files 文件全路径名称
     * @return
     */
    public static boolean deleteFiles(String... files) {
        log.info("deleteFiles :: files:: " + Arrays.toString(files));
        boolean success = false;
        try {
            File tmp = null;
            for (String fileNmae : files) {
                tmp = new File(fileNmae);
                FileUtils.forceDelete(tmp);
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
