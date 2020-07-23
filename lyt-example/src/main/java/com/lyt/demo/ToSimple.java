package com.lyt.demo;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * @ClassName ToSimple
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/22 18:03
 */

public class ToSimple {
    public static void main(String[] args) {
        String original = "生命不息，奮鬥不止";
        String result = ZhConverterUtil.convertToSimple(original);
        // 繁体转简体
        String a = ZhConverterUtil.convertToSimple("鄧麗君");
        // 简体转繁体
        String b = ZhConverterUtil.convertToTraditional("邓丽君");
        System.err.println(result);
        System.err.println(a);
        System.err.println(b);

    }
}