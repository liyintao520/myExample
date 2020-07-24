package com.lyt.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisTestService
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/4 10:57
 */
@Service
@Slf4j
public class RedisTestService {
    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate = null;

    public String testRedis() {
        stringStringRedisTemplate.opsForValue().set("Say redis", "Hello SpringBoot From Redis!", 60, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("Say redis");
    }
}
