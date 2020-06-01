package com.lyt.listTest;

import java.util.List;

/**
 * @ClassName InitListDb
 * @Description 初始化数据
 * @Author liyintao
 * @Date 2020/5/12 13:37
 */
public class InitListDb {
    /**
     * 初始化List<String>
     * @param list
     * @return
     */
    public static List<String> initList1 (List<String> list) {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        return list;
    }
    /**
     * 初始化List<String>
     * @param list
     * @return
     */
    public static List<String> initList2 (List<String> list) {
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        return list;
    }
    /**
     * 集合中本身就有重复值
     * @param list
     * @return
     */
    public static List<String> initList3 (List<String> list) {
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        return list;
    }


    public static List<String> initList4 (List<String> list) {
        list.add("2");
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        return list;
    }
}
