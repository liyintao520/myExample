package com.lyt.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Test
 * @Description 测试 ++ 操作在多线程下是否
 * @Author liyintao
 * @Date 2020/9/16 17:15
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        b();
    }


    public static void b () throws InterruptedException {
        UnsafeCounter uc = new UnsafeCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 设置 CountDownLatch 的计数器为 100，保证在主线程打印累加结果之前，100 个线程已经执行完累加
        CountDownLatch cdl = new CountDownLatch(1000);
        for(int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                uc.add();
                // 每一个线程执行完累加操作，都将计数器减 1
                cdl.countDown();
            });
        }
        // 主线程等待，直到 cdl 的计数器为0
        cdl.await();
        System.out.println("计数器执行完100次累加后值为：" + uc.get());
    }
}
