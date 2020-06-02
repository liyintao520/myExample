package com.lyt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/web")
public class IndexController {
    /**
     * http://localhost:8888/web/index
     * @return
     */
    @GetMapping("/index")
    public String index(){
        log.info("【测试】展示首页面信息");
        return "index";
    }

}
