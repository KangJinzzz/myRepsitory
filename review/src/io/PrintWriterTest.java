package io;

import java.io.*;
import java.util.Scanner;

public class PrintWriterTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fs = new FileInputStream(
                new File("D:\\编程app\\代码\\file\\demo1.txt"));
        BufferedReader br = new BufferedReader(
                new InputStreamReader(fs, "UTF-8"));

        char[] chars = new char[1024];
        int len = 0;
        while ((len = br.read(chars, 0, len)) != -1) {

        }
    }
}
