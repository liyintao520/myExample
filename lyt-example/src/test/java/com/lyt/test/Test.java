package com.lyt.test;

/**
 * @ClassName Test
 * @Description
 *  用java实现一个算法:要求不用循环和本地变量.给正整数n,以*2(2n,4n,8n)的方式递
 * @Author liyintao
 * @Date 2020/4/28 10:40
 */
public class Test {
    public static void main(String[] argv) {
        display(2001);
    }

    public static void display(int n) {
        System.out.println(n);
        if (n <= 5000) {
            // 当满足条件的时候，调用本方法继续执行。下面的[System.out.println(n);]继续等着
            display(n * 2);
        }
        // 当n大于5000的时候，依次输出。即 传值变量倒着输出。因为最后一个方法执行完，接着执行上个方法。
        System.out.println(n);
    }
}
