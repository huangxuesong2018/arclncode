package com.gupao.edu.vip.bio.tomcat.servlet;

import com.gupao.edu.vip.bio.tomcat.Request;
import com.gupao.edu.vip.bio.tomcat.Response;

import java.io.IOException;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class Test1Servlet extends AbstractServlet {
    @Override
    protected void doGet(Request request, Response response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(Request request, Response response) throws IOException {
        System.out.println("Test1Servlet");
        response.writer("hello,Test1");
    }
}
