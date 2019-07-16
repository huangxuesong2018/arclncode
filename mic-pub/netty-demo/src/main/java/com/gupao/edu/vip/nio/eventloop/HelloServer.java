package com.gupao.edu.vip.nio.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
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
        System.out.println("1");
        serverBootstrap.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer(){
                            @Override
                            protected void initChannel(Channel channel) throws Exception {
                                System.out.println("2");
                                ChannelPipeline pipeline = channel.pipeline();
                                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                                // 自己的逻辑Handler
                                 pipeline.addLast("handler", new HelloServerHandler());
                            }
                        });

        try {
            System.out.println("3");
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("4");
            f.channel().closeFuture().sync();
            System.out.println("5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
