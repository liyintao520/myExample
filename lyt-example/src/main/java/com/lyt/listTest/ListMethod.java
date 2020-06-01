package com.lyt.listTest;

import com.lyt.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListMethod
 * @Description TODO
 * @Author liyintao
 * @Date 2020/5/12 11:40
 */
@Slf4j
public class ListMethod {

    public static void main(String[] args) {
        List<String> list1 = InitListDb.initList1(new ArrayList<>());
        List<String> list2 = InitListDb.initList2(new ArrayList<>());
        List<String> list3 = InitListDb.initList3(new ArrayList<>());
        List<String> list4 = InitListDb.initList4(new ArrayList<>());
//        log.info("两个list是否相等：{}", testContainsAll(list1, list2));
//        log.info("两个list是否相等：{}", testContainsAll(list1, list3));
//        log.info("两个list是否相等：{}", testRemove(list1, list3));
        log.info("交集：{}", testRetainAll(list1, list4));
    }

    /**
     * 如果此列表包含指定集合的所有元素，则返回 true
     * 注意：有一个弊端-->就是list1 | list2中的数据本身就是重复的
     * @param list1
     * @param list2
     * @return
     */
    public static Boolean testContainsAll(List<String> list1, List<String> list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    /**
     * List中删除元素    remove和removeAll
     * @param list1
     * @param list2
     * @return
     */
    public static Boolean testRemove(List<String> list1, List<String> list2) {
        return list1.removeAll(list2);
    }

    /**
     * 取得两个List的交集，retainAll()方法
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> testRetainAll(List<String> list1, List<String> list2) {
        list1.retainAll(list2);
        log.info("list1 = {}", JsonUtil.toJson(list1));
        list2.retainAll(list1);
        log.info("list2= {}", JsonUtil.toJson(list2));
        return list1;
    }

}
