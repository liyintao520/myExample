package com.lyt.threadcoreknowledge.createthreads;

/**
 * 描述：     用Thread方式实现线程
 * 重写Thread的run()方法（集成Thread类）
 * java中类是单继承的  java接口是可以多继承的
 * 整个run()方法被重写了。可以看一下Thread的run()方法。746行
 */
public class ThreadStyle extends Thread{

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}





