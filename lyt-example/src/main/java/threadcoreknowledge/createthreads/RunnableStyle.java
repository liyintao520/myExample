package threadcoreknowledge.createthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：     用Runnable方式创建线程
 * 实现Runnable接口的run()方法，并吧Runnable实例传给Thread类
 * 从解耦的角度来说：这个比ThreadStyle实现线程更好
 * 资源节约。
 * java类是单继承的。
 * 方法本质对比：该方法最终调用target.run()       ThreadStyle：run()整个都被重写了。
 */
@Slf4j
public class RunnableStyle implements Runnable{

    // 自动补全：mac command + option + v     win Ctrl + Alt + v
    // 自动补全：mac command + option + f     win Ctrl + Alt + f
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
    // 这个是实现了run()方法
    @Override
    public void run() {
        log.info("用Runnable方法实现线程！！！");
    }
}

