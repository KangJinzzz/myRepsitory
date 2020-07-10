package web.tcp;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class TCPClient {
    private static final String IP = "localhost";
    private static final int PORT = 666;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP, PORT);
        Scanner sc = new Scanner(System.in);
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        OutputStream os = socket.getOutputStream();
        // 使用PrintWriter更方便
        PrintWriter pw = new PrintWriter(os, true);

        while (true) {
            System.out.println("输入向服务器发送的数据：");
            String line = sc.nextLine();
            pw.println(line);
            if (line.equals("down")) {
                System.out.println("关闭连接");
                socket.close();
                return;
            }
            System.out.println(br.readLine());
        }
    }

//    private static final String IP = "localhost";
//    private static final int PORT = 999;
//    public static void main(String[] args) throws IOException {
        //建立连接
//        Socket socket = new Socket(IP, PORT);
//
//        InputStream is = socket.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//        OutputStream os = socket.getOutputStream();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//        PrintWriter pw = new PrintWriter(os, true);
//        pw.println("我是你爸爸!");
//        bw.write("我是你爸爸！\n");
//        bw.flush();
//    }

}
