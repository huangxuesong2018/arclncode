package com.gupao.edu.vip.nio.talk.server;

import com.gupao.edu.vip.nio.talk.entity.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class MessageChannelAdapter extends ChannelInboundHandlerAdapter {
    private static Vector<Channel> vector = new Vector();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        vector.add(ctx.channel());
        System.out.println(ctx.channel().remoteAddress()+"上线"+",在线人数："+vector.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message)msg;
        System.out.println("服务端接收消息"+message);
        for (Iterator it = vector.iterator();it.hasNext();){
            Channel channel = (Channel)it.next();
            if (ctx.channel() == channel) continue;
            channel.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"下线"+cause.getMessage());
    }
}
