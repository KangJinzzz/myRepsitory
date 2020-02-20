package http;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Request {
    //请求方法：get或post
    private String method;
    //请求地址：对应服务器端对应的服务
    private String url;
    //http版本
    private String version;
    //请求头
    private Map<String, String> headers = new HashMap<>();
    //请求参数
    private Map<String, String> parameters = new HashMap<>();


    /**
     *类似单例的写法，提供私有的构造方法
     */
    private Request() {

    }

    /**
     * 通过客户端发送的http请求数据，转换为request请求类；
     * 包装请求方法，url，http版本号，请求参数
     * @param inputStream
     * @return
     */

    public static Request buildRequest(InputStream inputStream) {
        Request request = new Request();
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            System.out.println("=============开始解析http请求=============");
            String requestLine = input.readLine();
            //处理请求行
            request.parseRequestLine(requestLine);
            //处理请求头
            String header;
            while ((header = input.readLine()) != null && header.length() != 0) {
                String[] parts = header.split(":");
                request.headers.put(parts[0].trim(), parts[1].trim());
                System.out.printf("请求头：%s=%s\n", parts[0].trim(), parts[1].trim());
            }
            //如果是POST方法提交，并且有Content-Length，表示请求数据中包含请求体，
            // 需处理请求体
            if ("POST".equalsIgnoreCase(request.method)
                    && request.headers.containsKey("Content-Length")) {
                int len = Integer.parseInt(request.headers.get("Content-Length"));
                char[] chars = new char[len];
                input.read(chars);
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
     * 解析请求行为method,url,version
     * @param requestLine
     */
    private void parseRequestLine(String requestLine) {
        if (requestLine == null)
            return;
        String[] parts = requestLine.split(" ");
        method = parts[0];
        url = parts[1];

        //如果url包含“？”，表示有请求参数，需处理
        int index = url.indexOf("?");
        if (index != -1) {
            //？前的是url，之后的是请求参数
            parseParameters(url.substring(index + 1));
            url = url.substring(0, index);
        }
        version = parts[2];
        System.out.printf("请求方法：%s, url：%s, 版本号：%s\n",method, url, version);
    }

    /**
     * 解析请求参数
     * 格式为key1=value1&k2=v2..
     * @param parameters
     */
    private void parseParameters(String parameters) {
        String[] parts = parameters.split("&");
        if (parts != null && parts.length != 0) {
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
    public String getHeader(String key) {
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
