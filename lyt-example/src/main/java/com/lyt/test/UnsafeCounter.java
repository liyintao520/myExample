package com.lyt.test;

/**
 * @ClassName UnsafeCounter
 * @Description TODO
 * @Author liyintao
 * @Date 2020/9/16 17:18
 */
public class UnsafeCounter {
    public int count = 0;

    public void add() {
        count++;
    }

    public int get() {
        return count;
    }
}
