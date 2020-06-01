package com.lyt.decimalFormatTest;

import com.lyt.comm.JedisConstants;

import java.text.DecimalFormat;

/**
 * @ClassName DecimalFormatTest
 * @Description DecimalFormat 格式化数据
 * @Author liyintao
 * @Date 2020/5/13 13:45
 */
public class DecimalFormatTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            DecimalFormat df = new DecimalFormat("0000");
            String orgCode = "000000" + df.format(i);
            String key = JedisConstants.SYS_ORGANIZATION_HASH + orgCode;
            System.out.println(key);
        }
    }
}
