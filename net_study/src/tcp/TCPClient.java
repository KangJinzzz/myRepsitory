package tcp;

import com.sun.corba.se.impl.logging.POASystemException;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    //private static final String HOST = "192.168.1.4";

    //本机默认域名就是localhost，ip就是127.0.0.1
    //localhost会通过本机c://windows/system/drivers/etc/hosts文件转换为ip
    private static final String HOST = "localhost";

    //private static final String HOST = "127.0.0.1";

    private static final int PORT = 9999;
    public static void main(String[] args) throws IOException {
        //建立了客户端到服务端一个TCP连接
        Socket soclet = new Socket(HOST, PORT);
    }
}
