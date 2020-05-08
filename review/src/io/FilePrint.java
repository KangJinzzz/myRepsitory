package io;

import java.io.File;

public class FilePrint {
    public static void main(String[] args) {
        String path = "C:\\Users\\KJW\\Desktop";
        File file = new File(path);
        listAllFiles(file);
    }

    //递归打印文件目录列表
    private static void listAllFiles(File file) {
        if (file.isDirectory()) {
            File[] result = file.listFiles();
            if (result != null) {
                for(File f : result) {
                    listAllFiles(f);    //目前的文件是目录，开始递归
                }
            }
        } else {    //是文件，直接打印
            System.out.println(file);
        }
    }

//    private static void print(File file) {
//        if (file.isDirectory()) {
//            File[] res = file.listFiles();
//            if (res != null) {
//                for (File f : res) {
//                    print(f);
//                }
//            }
//        } else {
//            System.out.println(file);
//        }
//    }
}
