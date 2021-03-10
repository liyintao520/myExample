package com.lyt.jschTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName ProcessTest
 * @Description 本地测试 命令执行脚本
 * @Author liyintao
 * @Date 2020/11/10 14:28
 * 注意：1、python脚本必须都用的是绝对路径（可以拼接）
 */
public class ProcessTest {
//    在使用 cmd /c 时不会弹出python输出窗口，
//    可以使用 cmd /k start 前缀以弹出一个新的控制台窗口并显示python输出
    public static void main(String[] args) {
        try {
            // TODO 为什么执行完之后 没有退出？
            System.out.println("start");
            String windowcmd = "cmd /k start python F:/datax/bin/datax.py F:/datax/job/liyintaoceshi.json";
            System.out.println(windowcmd);
            Process pr = Runtime.getRuntime().exec(windowcmd);
            // 用输入输出流来截取结果
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
