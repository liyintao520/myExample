package com.lyt.mapTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MapTest
 * @Description 浅拷贝--深拷贝 demo
 * @Author liyintao
 * @Date 2020/7/22 10:59
 */
public class MapTest {
    public static void main(String[] args) {
        Map map1 = new HashMap();
        map1.put("a", 1);
        Map map2 = map1;
        map2.put("b", 2);
        System.err.println(map1.get("b"));


        List a = new ArrayList();
        a.add(1);
        List b = a;
        b.add(2);
        System.err.println(a.toString());

    }
}
