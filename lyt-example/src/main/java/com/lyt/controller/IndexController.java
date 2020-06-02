package com.lyt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
//返回页面要使用Controller注解
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("")
    public String index() {
        return "index/index";
    }
}
