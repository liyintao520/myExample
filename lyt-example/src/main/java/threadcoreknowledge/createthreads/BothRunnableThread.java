package threadcoreknowledge.createthreads;

/**
 * 描述：     同时使用Runnable和Thread两种实现线程的方式
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //传进去了，但是没有被执行。因为被下面的重写的run()覆盖掉了
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
