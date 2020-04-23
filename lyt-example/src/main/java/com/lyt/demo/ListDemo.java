package com.lyt.demo;

import com.lyt.util.JsonUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ListDemo
 * @Description JDK8 list集合操作
 * @Author liyintao
 * @Date 2020/4/23 14:24
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(JsonUtil.toJson(filtered));
    }
}
