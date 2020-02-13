package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        //启动服务器
        ServerSocket serverSocket = new ServerSocket(PORT);

        //循环获取新的客户端连接
        while (true) {
            //阻塞，等待新的客户端连接
            Socket socket = serverSocket.accept();

            //处理这个连接的业务，这个业务可能会发生阻塞
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            //1.先接收，再然后打印
            //2.响应给客户端一个数据，表示已接收到消息
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("服务器接收到数据：" + line);
                //write将数据写入缓冲区
                bw.write("我已接收到了" + line + "消息");
                //需要刷新缓冲区，发送数据
                bw.flush();
            }

        }
    }
}
