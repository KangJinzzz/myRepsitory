package http;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //以输出文件夹作为相对路径
//        File f = new File("..login.html");
//        System.out.println(f.exists());

        //class.getResourceAsStream以
//        InputStream is2 = Test.class.getResourceAsStream("login.html");
//        System.out.println(is2);

        //推荐使用ClassLoader.getResourceAsStream()作为资源文件输入流
        //ClassLoader以编译输出文件夹根目录作为相对的标准位置
        InputStream is = Test.class.getClassLoader().getResourceAsStream("login.html");
        System.out.println(is);
    }
}
