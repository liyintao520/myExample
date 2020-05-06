package com.lyt.test.md5;

import com.lyt.util.MD5Util;

/**
 * @ClassName Md5Test
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/28 18:28
 */
public class Md5Test {
    public static void main(String[] args) {
        String pwd = MD5Util.getMD5Password("123456");
        System.out.println(pwd);
    }
}
