package web.tcp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    private static final int PORT = 666;
    private static final ExecutorService POOL = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        //启动TCP服务器
        ServerSocket serverSocket = new ServerSocket(PORT);
        //循环获取新的客户端连接
        while (true) {
            //调用accept()方法，与客户端连接后返回一个socket对象与客户端进行发送和接收字节流，
            //accept()是一个阻塞方法，没有获取到连接会一直阻塞在这里
            Socket socket = serverSocket.accept();
            String clientName = socket.getInetAddress().getHostName().toString();

            //多线程版
            POOL.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //通过socket获取输入、输出字节流
                        InputStream is = socket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                        OutputStream os = socket.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                        String line;
                        //阻塞等待客户端传古来的新的数据
                        while ((line = br.readLine()) != null) {
                            if (line.equals("down")) {
                                System.out.println(clientName + "关闭连接");
                                socket.close();
                                break;
                            } else {
                                System.out.println("服务端接收到数据： " + line);
                                bw.write(clientName + "已经收到了消息：" + line + "\n");
                                //write()只是把数据放到了缓冲区，调用flush()才会把缓冲区中的数据发送
                                bw.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }


    }

//    private static final int PORT = 666;
//
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(PORT);
//        Socket socket = serverSocket.accept();
//
//        InputStream is = socket.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//        OutputStream os = socket.getOutputStream();
//        PrintWriter pw = new PrintWriter(os, true);
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println("客户端发送收数据：" + line);
//            pw.println("服务器已接收数据" + line);
//        }
//    }
}
