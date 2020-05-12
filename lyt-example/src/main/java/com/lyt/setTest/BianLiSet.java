package com.lyt.setTest;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName BianLiSet
 * @Description Java HashSet遍历的2种方法
 * @Author liyintao
 * @Date 2020/5/7 10:54
 */
@Slf4j
public class BianLiSet {

    public static void main(String[] args) {
        one(InitSetDb.getSet());
        two(InitSetDb.getSet());
    }

    /**
     * 1. Iterator迭代方式遍历
     * @param set
     */
    public static void one (Set<String> set) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 2. for循环方式遍历
     * @param set
     */
    public static void two (Set<String> set) {
        for (String s:set) {
            System.out.println(s);
        }
    }
}
