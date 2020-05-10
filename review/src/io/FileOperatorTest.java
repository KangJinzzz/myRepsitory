package io;

import org.junit.Test;

import java.io.*;

public class FileOperatorTest {

    public static void main(String args[]) throws IOException {
        String s;
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符串, end结束。");
        // 读取字符
        do {
            s = br.readLine();
            System.out.println(s);
        } while (!s.equals("end"));
    }
}
