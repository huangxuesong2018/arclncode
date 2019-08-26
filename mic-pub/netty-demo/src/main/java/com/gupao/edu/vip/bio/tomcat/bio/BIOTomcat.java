package com.gupao.edu.vip.bio.tomcat.bio;

import com.gupao.edu.vip.bio.tomcat.servlet.AbstractServlet;
import com.gupao.edu.vip.bio.tomcat.servlet.Test1Servlet;
import com.gupao.edu.vip.bio.tomcat.servlet.Test2Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 用ServerSocket实现一个简单的Tomcat
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class BIOTomcat {
    private static int PORT;
    private static ServerSocket serverSocket;
    private static Map<String,AbstractServlet> servletMap = new HashMap<>();
    static {
        servletMap.put("/test1",new Test1Servlet());
        servletMap.put("/test2",new Test2Servlet());
    }
    public BIOTomcat(int PORT) {
        this.PORT = PORT;
    }

    //1，初始化 servlet,解析xml配置文件
    //2，启动服务，打开监听，ServerSocket 端口8080
    public void start(){
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Tomcat 启动，监听端口："+PORT);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("监听到"+socket.getInetAddress());
                process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void process(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        InputStreamRequest request = new InputStreamRequest(in);
        OutputStreamResponse response = new OutputStreamResponse(out);

        String url = request.getUrl();
        if (servletMap.containsKey(url)){
            AbstractServlet servlet = servletMap.get(url);
            servlet.service(request,response);
        }else {
            response.writer("404");
        }
        out.flush();
        out.close();
        in.close();
        socket.close();
        System.out.println("socket关闭");
    }

    private void close(){
        if (!serverSocket.isClosed()){
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Tomcat 关闭成功");
            }
        }
    }

    public static void main(String[] args) {
        new BIOTomcat(8080).start();
    }

}
