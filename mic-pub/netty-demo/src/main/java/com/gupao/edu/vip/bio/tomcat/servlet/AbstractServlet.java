package com.gupao.edu.vip.bio.tomcat.servlet;

import com.gupao.edu.vip.bio.tomcat.Request;
import com.gupao.edu.vip.bio.tomcat.Response;

import java.io.IOException;

/**
 * Servlet抽象类
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public abstract class AbstractServlet {
    public void service(Request request, Response response) throws IOException {
        if ("GET".equals(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }

    }


    protected abstract void doGet(Request request, Response response) throws IOException;
    protected abstract void doPost(Request request,Response response) throws IOException;
}
