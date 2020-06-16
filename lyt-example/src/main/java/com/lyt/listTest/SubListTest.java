package com.lyt.listTest;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubListTest
 * @Description list分段拆分
 * @Author liyintao
 * @Date 2020/6/16 10:52
 */
@Slf4j
public class SubListTest {


    /**
     *
     * @param list
     * @param groupSize
     * @return
     */
    private List<List<String>> splitListOne(List<String> list , int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        int num = ( length + groupSize - 1 )/groupSize ;
        List<List<String>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }

    /**
     * 使用guave实现    list分段拆分
     * @param list
     * @param groupSize
     * @return
     */
    private List<List<String>> splitList(List<String> list , int groupSize){
        log.error("使用guava实现--list分段拆分");
        return  Lists.partition(list, groupSize);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        List<List<String>> splitList = Lists.partition(list, 20);
        System.out.println(splitList.get(1));
    }
}
