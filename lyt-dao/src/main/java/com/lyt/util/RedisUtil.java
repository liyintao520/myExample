package com.lyt.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description 工具类
 * @Author liyintao
 * @Date 2019/4/4 上午11:32
 */

@Component
public class RedisUtil {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        Arrays.stream(keys).forEach(key->{
            remove(key);
        });
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {

        return redisTemplate.hasKey(key);
    }

    /**
     * 根据可以获取value
     * @param key
     * @return
     */
    public String get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object obj = operations.get(key);
        if(!isEmpty(obj)){
            return  obj.toString();
        } else{
            return "";
        }
    }

    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public Object getObject(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object obj = operations.get(key);
        return obj;
    }
    /**
     * 判断对象是否为空
     * 为空：true 非空：false
     * @param obj
     * @return
     */
    public boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            synchronized (operations) {
                operations.set(key, value);
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存(自增)
     * 使用spring-data-redis实现incr自增
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean setIncr(final String key, long value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            synchronized (operations) {
                operations.increment(key, value);
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存(自增)
     * 使用spring-data-redis实现incr自增
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public int setIncr(final String key) {
        long result = 1;
        try {
            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
            result = operations.increment(key, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int)result;
    }

    /**
     * 更新key-value型数据，数据有存活时间，并在更新时，更新数据存活时间
     * 存在就更新，不存在就写入
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean getAndSetExpire(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            Object obj = operations.get(key);
            if(null != obj){
                operations.getAndSet(key, value);
            }else{
                operations.set(key,value);
            }
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 更新key-value型数据
     * 存在就更新，不存在就写入
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean getAndSetExpire(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            Object obj = operations.getAndSet(key,value);
            if(null != obj){
                operations.getAndSet(key, value);
            }else{
                operations.set(key,value);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获得短信验证码校验次数
     * @param key  格式  "phone-checkType-checkCode-date"
     * @return
     * @throws Exception
     */
    public int getVerifyCounts(String key)  throws Exception {
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        String count = operations.get(key);
        if(!StringUtils.isBlank(count)){
            return Integer.valueOf(count);
        } else {
            return -1;
        }
    }

    /**
     * 设置短信验证码校验次数
     * @param key  格式  "phone-checkType-checkCode-date"
     * @param verifyCounts  校验次数
     * @return
     * @throws Exception
     */
    public void setVerifyCounts(String key,String verifyCounts,long expire) throws Exception{
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        synchronized (operations) {
            String count = operations.get(key);
            if(!StringUtils.isBlank(count)){
                operations.getAndSet(key, verifyCounts);
            }else{
                //有效期24小时
                operations.set(key,verifyCounts);
            }
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

    }

    /**
     * 有就原子加一，没有就写入
     * 登陆密码错误次数+1  key为空时设置为1
     * @param key key键
     * @return int 类型 counts 次数
     * @throws Exception
     */
    public int getCounts(String key,int timeout) throws Exception{
        long counts = 1;
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        synchronized (operations) {
            counts = operations.increment(key, 1L);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            return (int)counts;
        }
    }


    // TODO redis 对支持它的五种类型的基本操作 【redis缓存工具类】
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    public <T> ValueOperations<String, T> setCacheObject(String key, T value, long timeout) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        if(timeout == 0) {
            operation.set(key, value);
        } else {
            operation.set(key, value, timeout, TimeUnit.SECONDS);
        }
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperation.index(key, i));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> boundSetOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            boundSetOperation.add(it.next());
        }
        return boundSetOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }


    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<Integer, T> getCacheIntegerMap(String key) {
        Map<Integer, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
}