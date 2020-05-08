package io;

import java.io.File;

public class FileDemo3 {
    public static void main(String[] args) {
        File file = new File("D:\\编程app\\代码");
        if (file.exists()) {
            File[] result = file.listFiles();
            for (File f : result) {
                System.out.println(f);
            }
        }
    }
}
