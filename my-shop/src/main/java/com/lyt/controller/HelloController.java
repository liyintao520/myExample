package com.lyt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/")
//@Slf4j
public class HelloController {

    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;

    @GetMapping
    public String sayHello() {
        return "Hello SpringBoot！";
    }

    @GetMapping("/redis")
    public String sayRedisHello() {

        stringStringRedisTemplate.opsForValue().set("Say redis", "Hello SpringBoot From Redis!", 5, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("Say redis");
    }
}