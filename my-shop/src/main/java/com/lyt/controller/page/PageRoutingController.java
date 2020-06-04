package com.lyt.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName PageRoutingController
 * @Description 相关页面跳转
 * @Author liyintao
 * @Date 2020/6/3 14:50
 */
@Slf4j
//返回页面要使用Controller注解
@Controller
@RequestMapping("/page")
public class PageRoutingController {
    /**
     * http://localhost:8020/page/uploadFilePage
     * @return
     */
    @RequestMapping("/uploadFilePage")
    public String uploadFilePage() {
        log.info("进入上传页面");
        return "uploadFilePage";
    }
    /**
     * http://localhost:8020/page/index
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        log.info("测试首页");
        return "index/index";
    }

}

