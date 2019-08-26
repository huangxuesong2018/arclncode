package com.gupao.edu.vip.nio.talk.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class SendChannel {
    private static int PORT;
    private static String HOST;
    private Channel channel;

    public SendChannel(String host,int port) {
        HOST = host;
        PORT = port;
    }

    public void start(){
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        bootstrap.group(workGroup);
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)//TCP长连接
                 .handler(new ChannelInitializer() {
                     @Override
                     protected void initChannel(Channel ch) throws Exception {
                         ChannelPipeline pipeline = ch.pipeline();
                         /*pipeline.addLast(new StringDecoder());
                         pipeline.addLast(new StringEncoder());*/
                         //自定义解码
                         pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                         //自定义编码
                         pipeline.addLast(new LengthFieldPrepender(4));
                         pipeline.addLast("encode",new ObjectEncoder());
                         pipeline.addLast("decode",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                         pipeline.addLast(new SendChannelAdapter());
                     }
                 });

        try {
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            channel = future.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(String msg){
        channel.writeAndFlush(msg);
    }

    public Channel getChannel() {
        return channel;
    }
}
