package com.gupao.edu.vip.bio.tomcat.netty;

import com.gupao.edu.vip.bio.tomcat.Request;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class NettyRequest implements Request {
    private String url;
    private String method;

    public NettyRequest(HttpRequest request) {
        this.url = request.uri();
        this.method = request.method().name();
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
