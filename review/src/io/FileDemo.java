package io;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        String path = "D:\\编程app\\代码\\file\\";
        String name = "demo.txt";
        String pathname = path + name;

        File file = new File(pathname);
        boolean res = file.exists();
        System.out.println("文件" + pathname + "是否存在: " + res);
        res = file.isDirectory();
        System.out.println("文件" + pathname + "是否是目录: " + res);
        res = file.isFile();
        System.out.println("文件" + pathname + "是否是文件: " + res);
        if (file.delete()) {
            System.out.println("文件删除成功");
        } else {
            System.out.println("文件删除失败");
        }
    }
}
