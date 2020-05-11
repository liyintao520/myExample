package com.lyt.listTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListTest
 * @Description TODO
 * @Author liyintao
 * @Date 2020/5/7 11:00
 */
@Slf4j
public class ListTest {
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            list1.add("1");
            list1.add("2");
            list1.add("3");
            list1.add("4");
            list.removeAll(list);
            log.info("移除之后：size = {}", list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
