package com.gupao.edu.vip.nio.eventloop;

import com.gupao.edu.vip.nio.buffer.Buffers;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
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
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        System.out.println("这里是什么时候调用 的");
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast("handler", new HelloClientHandler());
                    }
                });

        try {
            /**
             * 向选择器注册通道
             *   sc.register(selector, interestSet, new Buffers(256, 256));
             */
            Channel ch = bootstrap.connect(host, port).sync().channel();
            String msg;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while ((msg = reader.readLine()) != null ){
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

    static class HelloClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client receive message: "+msg);
        }
    }
}
