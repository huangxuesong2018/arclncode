package com.gupao.edu.vip.nio.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-22
 */
public class HelloServer {
    private static int port = 8888;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer(){
                            @Override
                            protected void initChannel(Channel channel) throws Exception {
                                System.out.println("监听到数据请求..");
                                ChannelPipeline pipeline = channel.pipeline();
                                //自定义长度帧解码器
                                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                                // 自己的逻辑Handler
                                pipeline.addLast("handler", new HelloServerHandler());
                                System.out.println("数据请求处理完成");
                            }
                        });

        try {
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("服务监听启动,监听端口:"+port);
            f.channel().closeFuture().sync();
            System.out.println("监听关闭");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class HelloServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("chanelActive>>>>>>>，这里只会触发一次");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("server receive message:" + msg);
            ctx.channel().writeAndFlush("accept message "+ msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("get server exception :"+cause.getMessage());
        }

    }
}
