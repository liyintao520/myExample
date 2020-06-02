package com.lyt.threadcoreknowledge.startthread;

/**
 * 描述：     对比start和run两种启动线程的方式
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());

        };
        // 主线程执行的
        runnable.run();
        // 是调用子线程来实现启动线程的
        new Thread(runnable).start();
    }
}
