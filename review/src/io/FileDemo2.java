package io;

import java.io.File;
import java.io.IOException;

public class FileDemo2 {
    public static void main(String[] args) {
        String path = "D:\\编程app\\代码\\file\\";
        String dir_name = "demo_dir";
        String pathname = path + dir_name;

        File file = new File(pathname);
        File pfile = file.getParentFile();
        if (!pfile.exists()) {
            pfile.mkdirs();
            System.out.println("路径" + pfile.getAbsolutePath() + "不存在，创建");
        }
        if (!file.exists()) {

            file.mkdir();
        }
    }


}
