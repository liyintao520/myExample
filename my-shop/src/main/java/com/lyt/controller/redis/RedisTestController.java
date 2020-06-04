package com.lyt.controller.redis;

import com.lyt.service.file.FileService;
import com.lyt.service.redis.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedisTestController
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/4 10:55
 */
@Slf4j
@RestController("/redis")
public class RedisTestController {

    @Autowired
    private RedisTestService redisTestService;

    @GetMapping("/redis")
    public String sayRedisHello() {
        log.info("HelloController-->sayRedisHello()...");
        return redisTestService.testRedis();
    }
}
