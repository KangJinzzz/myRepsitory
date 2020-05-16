package web.http;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Request {
    //请求方法：get或post
    private String method;
    //请求地址，对应服务端提供的服务路径
    private String url;
    //http版本
    private String version;
    //请求头
    private Map<String, String> headers = new HashMap<>();
    //请求参数
    private Map<String, String> parameters = new HashMap<>();
    /**
     * 类似单例的方法，提供私有的构造方法
     */
    private Request() {

    }

    /**
     * 通过客户端发送的请求数据转换为request请求类
     * 包装请求方法、url、版本号等
     * 类似单例的秀而发，提供私有的构造方法
     * @param inputStream
     * @return
     */
    public static Request buildRequest(InputStream inputStream) {
        Request request = new Request();
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            System.out.println("========================= 开始解析HTTP请求 =========================");
            String requestLine = input.readLine();
            request.parseRequestLine(requestLine);
            String header;
            //处理请求头
            while ((header = input.readLine()) != null && header.length() != 0) {
                String[] parts = header.split(":");
                request.headers.put(parts[0].trim(), parts[1].trim());
                System.out.printf("请求头：%s=%s\n", parts[0].trim(), parts[1].trim());
            }
            System.out.println();
            //如果是POST方法提交且有Content-Length，表示请求数据中包含请求体
            //需处理请求体
            if ("POST".equalsIgnoreCase(request.method)
                    && request.headers.containsKey("Content-Length")) {
                int len = Integer.parseInt(request.headers.get("Content-Length"));
                char[] chars = new char[len];
                input.read(chars,0, len);
                request.parseParameters(new String(chars));
            }
            System.out.print("请求参数：");
            for (Map.Entry<String, String> entry : request.parameters.entrySet()) {
                System.out.printf("%s=%s, ", entry.getKey(), entry.getValue());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("处理请求数据错误", e);
        }
        return request;
    }

    /**
     * 解析请求行
     * @param requestLine
     */
    private void parseRequestLine(String requestLine) {
        if (requestLine == null) {
            return;
        }
        String[] parts = requestLine.split(" ");
        method = parts[0];
        url = parts[1];
        //url包含？说明有请求参数
        int index = url.indexOf("?");
        if (index != -1) {
            parseParameters(url.substring(index + 1));
            url = url.substring(0, index);
        }
        version = parts[2];
        System.out.println("请求方法：" + method + " url" + url + " 版本号" + version);
    }

    /**
     * 解析请求参数:k1=v1&k2=v2....
     * @param parameters
     */
    private void parseParameters(String parameters) {
        String[] parts = parameters.split("&");
        if (parts != null && parts.length != 0) {
            //part为k1=v1
            for (String part : parts) {
                String[] params = part.split("=");
                this.parameters.put(params[0], params[1]);
            }
        }
    }

    /**
     * 根据请求头中的key获取value
     * @param key
     * @return
     */
    public String getHeader(String key){
        return headers.get(key);
    }

    /**
     * 根据请求参数中的key获取value
     * @param key
     * @return
     */
    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
