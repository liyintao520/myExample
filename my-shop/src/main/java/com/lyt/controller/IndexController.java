package com.lyt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {
    /**
     * http://localhost:8020/index/index
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        System.err.println("my-shop服务测试-->IndexController-->测试返回页面");
        //当浏览器输入index/index时，会返回 /templates/index.html页面
        return "index";
    }

    /**
     * http://localhost:8020/index/test
     * @return
     */
    @RequestMapping("/test")
    public String test() {
        System.err.println("my-shop服务测试-->IndexController-->测试返回页面");
        //当浏览器输入index/index时，会返回 /templates/index.html页面
        return "index/index";
    }
}
