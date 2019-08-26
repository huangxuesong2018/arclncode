package com.gupao.edu.vip.nio.talk.client;

import com.gupao.edu.vip.nio.talk.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class SendChannelAdapter extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已激活.......");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message)msg;
        System.out.println("消息来自["+message.getName()+"]:\t"+message.getMessage());
    }
}
