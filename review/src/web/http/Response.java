package web.http;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * http响应数据：
 *构建response对象，将输出流设置到该对象属性
 *把这个对象相关的数据设置进去，包括响应行、响应头、响应体
 * 调用刷新，输出流打印数据返回给客户端
 */
public class Response {
    private PrintWriter writer;
    //版本号
    private String version = "HTTP/1.1";
    //状态码
    private int status;
    //状态码描述
    private String message;
    //响应头
    private Map<String, String> headers = new HashMap<>();
    //响应体
    private StringBuilder body = new StringBuilder();
    private Response() {

    }
    public static Response buildeResponse(OutputStream outputStream) {
        Response response = new Response();
        response.writer = new PrintWriter(outputStream);
        return response;
    }

    //按行设置请求体数据到body中
    public void println(String line) {
        body.append(line + "\n");
    }

    /**
     * 输出流打印刷新响应数据给客户端
     */
    public void flush() {
        //打印响应行
        writer.println(version + " " + status + " " + message);

        //打印响应头
        //设置响应格式Content-Type(浏览器获取响应数据以后，按照什么类型来渲染或处理)
        writer.println("Content-Type: text/html; charset=UTF-8");
        if (body.length() != 0) {
            //正文的长度是二进制字节数组的长度
            writer.println("Content-Length: " + body.toString().getBytes().length);
        }
        //打印业务代码设置的响应头
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }
        writer.println();
        if (body.length() != 0) {
            writer.println(body);
        }
        //刷新输出流
        writer.flush();
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void build200() {
        status = 200;
        message = "OK";
    }
    public void build404() {
        status = 404;
        message = "Not Found";
    }
    public void build500() {
        status = 500;
        message = "Internal Server Error";
    }
    public void build307() {
        status = 307;   //301,302也可
        message = "Send Redirect";
    }
    public void build405() {
        status = 405;
        message = "Method Not Allowed";
    }
}
