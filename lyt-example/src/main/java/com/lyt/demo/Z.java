package com.lyt.demo;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/23 16:01
 */
class X {
    Y y = new Y();

    public X() {
        System.out.println("X");
    }
}

class Y {
    public Y() {
        System.out.println("Y");
    }
}

public class Z extends X {
    Y y = new Y();

    public Z() {
        System.out.println("Z");
    }

    public static void main(String[] args) {
        new Z();
    }
}
