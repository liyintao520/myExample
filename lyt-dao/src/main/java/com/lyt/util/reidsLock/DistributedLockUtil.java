package com.lyt.util.reidsLock;

/**
 * @ClassName DistributedLockUtil
 * @Description TODO
 * @Author liyintao
 * @Date 2019/9/3 7:00 PM
 *
 * <h1>分布式锁工具类</h1>
 */
public class DistributedLockUtil {

    /**
     * 获取分布式锁
     * 默认获取锁15s超时, 锁过期时间15s
     */
    public static IDistributedLock getDistributedLock(String lockKey) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey);
    }

    /**
     * 获取分布式锁
     */
    public static IDistributedLock getDistributedLock(String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, timeoutMsecs);
    }

    /**
     * 获取分布式锁
     */
    public static IDistributedLock getDistributedLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, expireMsecs, timeoutMsecs);
    }

    /**
     * 对 key 进行拼接
     */
    private static String assembleKey(String lockKey) {
        return String.format("imooc_analyze_%s", lockKey);
    }
}
