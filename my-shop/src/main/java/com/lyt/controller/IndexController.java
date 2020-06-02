package com.lyt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping
public class IndexController {
    /**
     * http://localhost:8020/index
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index"; //当浏览器输入/index时，会返回 /templates/index.html页面
    }

}
