package com.lyt.listTest;

import com.lyt.module.user.entity.User;
import com.lyt.util.MaxExcelWriter;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ListBySortTopTest
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/22 10:04
 */
public class ListBySortTopTest {

    public static void main(String[] args) {
        try {
            List<User> list = new ArrayList<User>();
            for (int i = 0; i < 100000; i++) {
                User people = new User("admin" + i, "password" + i, new Date(), new Date());
                list.add(people);
            }
            MaxExcelWriter writer = new MaxExcelWriter();
            byte[] buff = writer.exportExcel("测试例子", new String[]{"ID", "用户名", "密码","创建时间","修改时间"}, list, null);
            OutputStream os = new FileOutputStream(new File("D:/excel/蛋蛋老骚逼.xlsx"));
            IOUtils.write(buff, os);
            System.err.println("创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
