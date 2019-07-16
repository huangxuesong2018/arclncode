package com.gupao.edu.vip.nio.eventloop;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-22
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("chanelActive>>>>>>>");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server receive message:" + msg);
        ctx.channel().writeAndFlush("accept message "+ msg);
       // ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("get server exception :"+cause.getMessage());
    }

}
