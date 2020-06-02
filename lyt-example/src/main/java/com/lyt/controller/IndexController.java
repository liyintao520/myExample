package com.lyt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/web")
public class IndexController {
    /**
     * http://localhost:8888/web/index
     * @return
     */
    @GetMapping("/index")
//    @ResponseBody
    public String index(){
        log.info("【lyt-example】展示首页面信息");
//        return "index.html";
        return "index";
    }

}
