package com.lyt.listTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName JudgeList
 * @Description 比较两个List是否相等，长度和内容都相等
 * @Author liyintao
 * @Date 2020/5/12 14:33
 */
public class JudgeList {
    public static void main(String[] args){
        compareList1();
        compareList2();
    }

    /**
     * list中存在重复数据,且顺序不一样
     * @return
     */
    public static boolean compareList1() {
        boolean flg = false;
        List<String> list1 = InitListDb.initList2(new ArrayList<>());
        List<String> list2 = InitListDb.initList3(new ArrayList<>());
        //list进行排序
        Collections.sort(list1);
        Collections.sort(list2);
        flg = list1.equals(list2);
        //输出比较结果
        System.out.println(flg);
        return flg;
    }

    /**
     * list中存在重复数据,且顺序不一样
     * @return
     */
    public static boolean compareList2() {
        boolean flg = false;
        List<String> list1 = InitListDb.initList2(new ArrayList<>());
        List<String> list2 = InitListDb.initList3(new ArrayList<>());
        list1.sort((o1, o2) -> o1.compareTo(o2));
        list2.sort((o1, o2) -> o1.compareTo(o2));

        if(list1.size() == list2.size() && list1.containsAll(list2)){
            flg = true;
        }
        System.out.println(flg);
        return flg;
    }
}
