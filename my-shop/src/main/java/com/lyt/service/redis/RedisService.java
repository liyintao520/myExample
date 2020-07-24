package com.lyt.service.redis;

import com.google.common.collect.Maps;
import com.lyt.util.DataReturn;
import com.lyt.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName RedisService
 * @Description TODO
 * @Author liyintao
 * @Date 2019/4/4 下午4:23
 */
@Slf4j
@Service
@Transactional(readOnly = false)
public class RedisService {

    @Autowired
    private RedisUtil redisUtil;

    public DataReturn test(String id) {
        DataReturn dr = new DataReturn();
        try {
            log.info("【根据key获取值】nowCount = {}", id);
            Map map = Maps.newHashMap();
            String nowCount = null;
            if (!redisUtil.exists(id)) { // 不存在就写入
                redisUtil.getAndSetExpire(id, "insert", 60L);
            } else { // 存在就修改
                redisUtil.getAndSetExpire(id, "update", 60L);
            }
            nowCount = (String) redisUtil.get(id); // 17540001025_2018-05-17_loginCount
            log.info("缓存:{}", nowCount);
            map.put(id, nowCount);
            dr.setCode("0");
            dr.setData(map);
        } catch (Exception e) {
            dr.setCode("2");
            log.info("系统异常！");
            e.printStackTrace();
        }
        return dr;
    }
}
