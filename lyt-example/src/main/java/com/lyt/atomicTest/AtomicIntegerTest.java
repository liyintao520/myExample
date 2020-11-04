package com.lyt.atomicTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest
 * @Description 并发包下的原子类测试
 * @Author liyintao
 * @Date 2020/8/21 10:18
 * AtomicInteger 类主要利用 CAS (compare and swap) + volatile 和 native 方法来保证原子操作，从而避免synchronized 的高开销，执行效率大为提升
 */
public class AtomicIntegerTest {
//    AtomicInteger 类常用方法
//    public final int get() //获取当前的值
//    public final int getAndSet(int newValue)//获取当前的值，并设置新的值
//    public final int getAndIncrement()//获取当前的值，并自增
//    public final int getAndDecrement() //获取当前的值，并自减
//    public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
//    boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置 为输入值（update）
//    public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线 程在之后的一小段时间内还是可以读到旧的值。
    // 使用 AtomicInteger 之后，不用对 increment() 方法加锁也可以保证线程安全。
    private AtomicInteger count = new AtomicInteger();

    //使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
