package tcp;

import com.sun.corba.se.impl.logging.POASystemException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    //private static final String HOST = "192.168.1.4";

    //本机默认域名就是localhost，ip就是127.0.0.1
    //localhost会通过本机c://windows/system/drivers/etc/hosts文件转换为ip
    private static final String HOST = "localhost";

    //private static final String HOST = "127.0.0.1";

    private static final int PORT = 9999;
    public static void main(String[] args) throws IOException {
        //建立了客户端到服务端一个TCP连接
        Socket socket = new Socket(HOST, PORT);

        //处理这个连接的业务，这个业务可能会发生阻塞
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
//        pw.println("hello,我来了");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine(); //这里去除了换行符
            //发送数据报到服务端
            pw.println(line); //println发送的数据会加上换行符
            //接收服务端的响应信息
            String response = br.readLine();
            System.out.println("接收到服务器响应" + response);
        }

    }
}
