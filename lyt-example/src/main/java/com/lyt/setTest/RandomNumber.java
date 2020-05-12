package com.lyt.setTest;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @ClassName RandomNumber
 * @Description 获取一个四位随机数，并且四位数不重复
 * @Author liyintao
 * @Date 2020/5/7 10:50
 */
@Slf4j
public class RandomNumber {
    /**
     * 获取一个四位随机数，并且四位数不重复
     *
     * @return Set<Integer>
     */
    public static Set<Integer> getRandomNumber() {
        // 使用SET以此保证写入的数据不重复
        Set<Integer> set = new HashSet<Integer>();
        // 随机数
        Random random = new Random();
        while (set.size() < 4) {
            // nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
            // 和指定值（不包括）之间均匀分布的 int 值。
            set.add(random.nextInt(10));
        }
        return set;
    }

    public static void main(String[] args) {
        Set<Integer> set = getRandomNumber();
        // 使用迭代器
        Iterator<Integer> iterator = set.iterator();
        // 临时记录数据
        String temp = "";
        while (iterator.hasNext()) {
            temp += iterator.next();
        }
        log.error("temp = {}", temp);
    }
}
