package com.lyt.setTest;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName InitSetDb
 * @Description 初始化Set集合
 * @Author liyintao
 * @Date 2020/5/11 13:39
 */
public class InitSetDb {

    /**
     * 初始化一个Set<String>集合 </>
     * @return
     */
    public static Set<String> getSet () {
        Set<String> set = new HashSet<String>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add("d");
        set.add("d");
        return set;
    }
}
