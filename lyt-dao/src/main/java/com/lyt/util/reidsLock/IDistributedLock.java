package com.lyt.util.reidsLock;

/**
 * <h1>分布式锁接口</h1>
 * 只需要两个接口: 获取锁与释放锁
 */
public interface IDistributedLock {
    /**
     * <h2>获取锁</h2>
     * */
    boolean acquire();
    /**
     * <h2>释放锁</h2>
     * */
    void release();
}
