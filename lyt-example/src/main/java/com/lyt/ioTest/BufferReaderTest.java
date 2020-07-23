package com.lyt.ioTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName BufferReaderTest
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/23 16:51
 */
public class BufferReaderTest {
    public static void main(String[] args) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String s = input.readLine();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
