package com.gupao.edu.vip.nio.talk.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 聊天服务器 netty版本
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class TalkServer {
    private static int PORT;

    public TalkServer(int PORT) {
        this.PORT = PORT;
    }

    public void start(){
        ServerBootstrap server = new ServerBootstrap();
        EventLoopGroup mainGroup = new NioEventLoopGroup();//主线程
        EventLoopGroup workGroup = new NioEventLoopGroup();//工作线程
        //把主线程和工作线程加入组
        server.group(mainGroup,workGroup);
        server.channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)//TCP长连接
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //自定义解码
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                        //自定义编码
                        pipeline.addLast(new LengthFieldPrepender(4));
                        pipeline.addLast("encode",new ObjectEncoder());
                        pipeline.addLast("decode",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast(new MessageChannelAdapter());
                    }
                });
        try {
            ChannelFuture future = server.bind(PORT).sync();
            System.out.println("服务监听启动,监听端口:"+PORT);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("聊天室服务启动失败");
        }
    }
}
