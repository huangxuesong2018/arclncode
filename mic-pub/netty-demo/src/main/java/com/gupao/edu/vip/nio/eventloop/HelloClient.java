package com.gupao.edu.vip.nio.eventloop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-22
 */
public class HelloClient {
    public static String host = "127.0.0.1";
    public static int port = 8888;
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        System.out.println(1);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        System.out.println(2);
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast("handler", new HelloClientHandler());
                    }
                });


        try {
            System.out.println(3);
            // 连接服务端
            Channel ch = bootstrap.connect(host, port).sync().channel();
            System.out.println(4);
            String msg;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while ((msg = reader.readLine()) != null ){
                System.out.println("333333");
                ch.writeAndFlush(msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
