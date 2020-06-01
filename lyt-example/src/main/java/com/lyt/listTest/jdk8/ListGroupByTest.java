package com.lyt.listTest.jdk8;

import com.lyt.util.JsonUtil;

/**
 * @ClassName ListGroupByTest
 * @Description list分组测试demo
 * @Author liyintao
 * @Date 2020/5/14 10:19
 */
public class ListGroupByTest {

    public static void main(String[] args) {
        String str = "0000000092_1_6";
        String[] strArr = str.split("_");
        System.out.println(JsonUtil.toJson(str));
    }
}
