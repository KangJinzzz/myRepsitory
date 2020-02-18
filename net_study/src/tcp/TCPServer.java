package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TCPServer {
    private static final int PORT = 9999;

    /**
     * 原生线程池创建方式
     * 参数1：核心线程数（正式工）
     * 菜蔬2：最大线程数（正式工+临时工）
     * 参数3+4：一定数量的时间+时间单位，在时间内，临时工的线程没有任务处理，就把临时工给解雇电（关闭线程）
     * 参数5：无边界的工作队列
     * 参数6：代表任务数量超出最大值，线程池应该怎么做（4中策略，现在简单了解）
     */
//    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(
//                0, Integer.MAX_VALUE, 30, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(), new ThreadPoolExecutor.CallerRunsPolicy());

    //线程池中使用的线程，在参数3+4的时间范围内，可以重用
    // 有新任务进来需要处理，此时有正式工线程空闲的，就让正式工处理，
    // 否则让临时工处理，如果都没有空闲的就创建新的线程处理（依赖具体是哪种线程池的实现）
    // （新的线程加入正式工或临时工）

    //单个线程池
//    private static final ExecutorService EXE = Executors.newSingleThreadExecutor();
    //可缓存的：正式工编制为0，都是临时工
    private static final ExecutorService EXE = Executors.newCachedThreadPool();
    //定时任务的
//    private static final ExecutorService EXE = Executors.newScheduledThreadPool();
    //限定大小的
//    private static final ExecutorService EXE = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws IOException {
        //启动服务器
        ServerSocket serverSocket = new ServerSocket(PORT);

        //循环获取新的客户端连接
        while (true) {
            //阻塞，等待新的客户端连接
            Socket socket = serverSocket.accept();

            //多线程处理，可以同时连接多个客户端
            //需要使用newCachedThreadPool这种类型，如果使用固定大小的线程池，超出任务数会造成阻塞
            EXE.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //处理这个连接的业务，这个业务可能会发生阻塞
                        InputStream is = socket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                        OutputStream os = socket.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                        //1.先接收，再然后打印
                        //2.响应给客户端一个数据，表示已接收到消息


                        String line;
                        //阻塞等待读取新的数据，readLine是读取换行符之前的数据
                        while ((line = br.readLine()) != null) { //需要IO流关闭或客户端方法返回（IO流关闭）会推出循环
                            System.out.println("服务器接收到数据：" + line);
                            //write将数据写入缓冲区
                            bw.write("我已接收到了" + line + "消息\n");
                            //需要刷新缓冲区，发送数据
                            bw.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
    }
}
