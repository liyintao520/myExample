package com.lyt.demo;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/23 15:15
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("main输出：" + new B().getValue());
    }

    static class A {
        protected int value;
        public A (int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value = value;
        }
        public int getValue () {
            try {
                value ++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println("A输出：" + value);
            }
        }
    }
    static class B extends A {
        public B () {
            super(5);
            setValue(getValue() -3);
        }
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
