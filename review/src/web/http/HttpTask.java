package web.http;

import java.io.*;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpTask implements Runnable{
//    private Socket socket;
    private Request request;
    private Response response;
    //服务端保存session的数据结构：还可以保存在其他地方，如redis中间键
    private static ConcurrentMap<String, String> SESSIONS = new ConcurrentHashMap<>();

    private static final String SESSION_KEY = "SESSIONID";

    public HttpTask(Socket socket) {
//        this.socket = socket;
        try {
            //通过客户端发送报文的输入流创建Http请求对象
            request = Request.buildRequest(socket.getInputStream());
            response = Response.buildeResponse(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("客户端连接的IO流出错", e);
        }
    }


    @Override
    public void run() {
        try {
            //1.根据解析出的request对象中的属性，来进行逻辑处理
            //2.将要返回的数据设置到response对象中
            //3.刷新响应信息，返回给客户端

            //调整业务逻辑:
            //1.url为根路径/,访问首页
            if ("/".equals(request.getUrl())) {
                response.build200();
                response.println("<h2>Http服务器首页</h2>");
                return;
            }

            //2.根据url在webapp文件夹下去找是否存在这个资源，如果存在就返回该资源内容
            //  改造：读取项目中login.html文件内容，返回给客户端，相对路径读取:
            //  1).html文件所在webapp需要设置为resources资源文件夹
            //  2).通过getClassLoader().getResourceAsStream()获取文件的输入流（相对路径）
            //http请求行url中开始以/开始，所以./就表示当前目录
            InputStream is = this.getClass().getClassLoader()
                    .getResourceAsStream("." + request.getUrl());
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String content;
                while ((content = br.readLine()) != null) {
                    response.println(content);
                }
                response.build200();
            } else if ("/login".equals(request.getUrl())){
                //1.只接受post请求方法，否则返回405
                if (!"post".equalsIgnoreCase(request.getMethod())) {
                    response.build405();
                    response.println("不支持的请求方法：" + request.getMethod());
                } else {
                //2.校验用户名密码
                    response.build200();
                    response.println("请求的数据：username="
                            + request.getParameter("username")
                            +"password=" + request.getParameter("password"));
                    //session：将用户信息保存到服务器，并返回给客户端
                    String sessionId = UUID.randomUUID().toString();
                    SESSIONS.put(sessionId, request.getParameter("username")
                        + "," + request.getParameter("password"));

                    response.addHeader("Set-Cookie", SESSION_KEY + "=" + sessionId);
                }
            }
            //敏感的url，没登录的时候，不能访问
            //浏览器传入的数据：header中为（Cookie：SESSIONID=xxxx;k2=v2;k3=v3...）
            else if ("/sensitive".equals(request.getUrl())) {
                String content = "<p>该用户没有登录</p>";
                String sessionInfo = request.getHeader("Cookie");
                if (sessionInfo != null && sessionInfo.trim().length() != 0) {
                    String[] cookieInfos = sessionInfo.split(";");
                    if (cookieInfos != null) {
                        for (String cookieInfo : cookieInfos) {
                            String[] cookieArray = cookieInfo.trim().split("=");
                            if (SESSION_KEY.equals(cookieArray[0])) {
                                String userInfo = SESSIONS.get(cookieArray[1]);
                                if (userInfo != null) {
                                    content = "获取到用户的信息：" + userInfo;
                                    System.out.println("============" + content);
                                }
                            }
                        }
                    }
                }
                response.build200();
                response.println(content);
            } else {    //服务器不提供该url的服务
                response.build404();
                response.println("找不到资源");
            }
            //返回数据给客户端
            response.flush();
        } catch (Exception e) { //捕获所有异常，表示服务器异常
            e.printStackTrace();
            response.build500();
            response.println("服务器出错");
        } finally {
             response.flush();
        }
    }
}
