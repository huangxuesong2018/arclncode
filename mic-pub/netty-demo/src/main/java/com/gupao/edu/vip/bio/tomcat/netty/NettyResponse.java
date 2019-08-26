package com.gupao.edu.vip.bio.tomcat.netty;

import com.gupao.edu.vip.bio.tomcat.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class NettyResponse implements Response {
    ChannelHandlerContext ctx;

    public NettyResponse(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void writer(String context) throws IOException {
        try{
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(context.getBytes("UTF-8"))
            );
            response.headers().set("Content-Type","text/html");
            ctx.write(response);
        }finally {
            ctx.flush();
            ctx.close();
        }

    }
}
