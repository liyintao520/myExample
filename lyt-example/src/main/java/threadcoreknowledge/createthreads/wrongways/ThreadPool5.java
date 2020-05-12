package threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 【wrongways】该包下都是典型的观点错误的例子（不是说代码错误，是创建线程的方式只有两种new Thread   实现Runnable接口）
 * 表面上是多种多样，但是本质实现都是那两种
 * 描述：     线程池创建线程的方法
 */
public class ThreadPool5 {


    public static void main(String[] args) {
        // 表象看到的是线程池创建的。事实上线程池内部也是新建了一个Thread 来执行这些任务的
        // 源码：Executors第611行
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            // 反复的往线程之中添加一个任务，执行该任务。
            executorService.submit(new Task() {
            });
        }
    }
}

/**
 * 实现任务的类。
 */
class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印线程的名字
        System.out.println(Thread.currentThread().getName());
    }
}
