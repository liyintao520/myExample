package com.lyt.testUtils;
import java.io.File;

import com.lyt.util.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
/**
 * 该包下都是测试工具类用的
 * @ClassName TestBase64Utils
 * @Description 测试【Base64Utils】
 * @Author liyintao
 * @Date 2020/6/1 11:46
 */
@Slf4j
public class TestBase64Utils {
    @Test
    public void testJpg2Base64(){
        Base64Utils utils = Base64Utils.getInstance();
        String str = utils.file2Base64(new File("C:/Users/Administrator/Desktop/图片/1.jpg"));
        System.out.println(str);
        log.info("图片转Base64--> str {}", str);
    }
    @Test
    public void testBase642File(){
        log.info("Base64转图片--> start...");
        Base64Utils utils = Base64Utils.getInstance();
        String str = utils.file2Base64(new File("C:/Users/Administrator/Desktop/图片/1.jpg"));
        utils.base64ToFile(str, new File("C:/Users/Administrator/Desktop/xx.jpg"));
        log.info("Base64转图片--> end...");
    }
}
