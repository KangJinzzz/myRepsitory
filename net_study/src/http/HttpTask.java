package http;


import java.io.*;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpTask implements Runnable {

//    private Socket socket;
    private Request request;

    private Response response;

    //服务端保存session的数据结构（线程安全的map）；还可以保存在其他地方，如redis中间键
    private static ConcurrentMap<String, String> SESSIONS = new ConcurrentHashMap<>();

    public HttpTask(Socket socket) {
//        this.socket = socket;
        try {
            //通过客户端发送报文的输入流（请求数据），创建Http请求对象
            request = Request.buildRequest(socket.getInputStream());
            response = Response.buildResponse(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("客户端连接的IO流出错", e);
        }
    }

    @Override
    public void run() {
        try {
            //1.根据解析出的request对象属性，来进行逻辑处理
            //2.在不同的逻辑中将要返回的数据设置到request对象中
            //3.刷新响应信息，返回给客户端

            //调整业务逻辑
            //1.url为根路径/,访问首页
            if ("/".equals((request.getUrl()))) {
                response.build200();
                response.println("<h2>Http服务器首页</h2>");
                return;
            }

            //2.根据url在webapp文件下去找是否存在这个资源，如果存在就返回该资源的内容
            //改造：读取项目中的login.html文件内容，并返回给客户端
            //读取文件：
            //相对路径读取步骤：
            //（1）.html文件所在的webapp需要设置resources资源文件夹，
            // 将webapp中所有的文件复制到编译的输出文件夹
            //（2）.通过ClassLoader.getResourceAsStream()获取文件的输入流（相对路径）

            InputStream is = this.getClass().getClassLoader()
                    .getResourceAsStream("." + request.getUrl());//http请求行url中url以/开始
            //如果存在，就设置该资源内容到response对象
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is
                        ,"UTF-8"));
                String content;
                while ((content = br.readLine()) != null) {
                    response.println(content);
                }
                response.build200();

            } else if ("/login".equals(request.getUrl())) {
                //1.只接受post请求方法，否则405
                if (!"post".equalsIgnoreCase(request.getMethod())) {
                    response.build405();
                    response.println("不支持的请求方法：" + request.getMethod());
                } else {
                    //2.检验用户名密码对不对，校验通过返回（我们这里省略校验）
                    response.build200();
                    response.println("请求的数据："
                            + "username= " + request.getParameter("username")
                            + "password= " + request.getParameter("password"));
                    //Session：将用户信息保存到服务器，并且返回给客户端
                    String sessionId = UUID.randomUUID().toString();
                    SESSIONS.put(sessionId, request.getParameter("username")
                        + "," + request.getParameter("password"));
                    //第一种：自己定义header头(需要客户端代码如：html，js代码中实现header解析和设置)
//                    response.addHeader("SESSIONID",sessionId);
                    //第二种：设置到Cookie中,之后访问每个url都会在请求中包含Cookie=sessionId
                    response.addHeader("Set-Cookie",sessionId);
                }
            //敏感的url，没有登录不能访问
            } else if ("/sensitive".equals(request.getUrl())) {
                String sessionId = request.getHeader("SESSIONID");
                String userInfo = SESSIONS.get(sessionId);
                System.out.println("================获取到用户的信息："+ userInfo);
            } else { //以上所有路径都找不到，说明我们服务器不提供该url的服务，返回404
                response.build404();
                System.out.println("找不到资源");
            }
        } catch (IOException e) { //捕获所有异常，表示的是服务器异常，需要返回500
            e.printStackTrace();
            response.build500();
            response.println("服务器出错");
        } finally { //需要刷新响应数据
            //返回数据给客户端
            response.flush();
        }

    }
}
