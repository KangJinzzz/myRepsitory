package io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileDemo1 {
    public static void main(String[] args) {
        String path = "D:\\编程app\\代码\\file\\";
        String name = "demo1.txt";
        String pathname = path + name;
        File file = new File(pathname);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("文件创建成功");
            } catch (IOException e) {
                System.out.println("文件创建失败");
            }
        } else {
            System.out.println("文件已存在，不需要创建");
        }
        System.out.println("文件" + name + "size: " + file.length());
        System.out.println("文件" + name + " 最近修改时间：" + new Date(file.lastModified()));
        File file1 = new File(".");
        System.out.println(file1.getAbsolutePath());
    }
}
