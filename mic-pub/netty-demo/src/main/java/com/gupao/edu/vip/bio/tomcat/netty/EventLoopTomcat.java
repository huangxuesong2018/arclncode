package com.gupao.edu.vip.bio.tomcat.netty;

import com.gupao.edu.vip.bio.tomcat.servlet.AbstractServlet;
import com.gupao.edu.vip.bio.tomcat.servlet.Test1Servlet;
import com.gupao.edu.vip.bio.tomcat.servlet.Test2Servlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 用netty实现一个简单的Tomcat
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class EventLoopTomcat {
    private static int PORT;
    private static Map<String,AbstractServlet> servletMap = new HashMap<>();
    static {
        servletMap.put("/test1",new Test1Servlet());
        servletMap.put("/test2",new Test2Servlet());
    }
    public EventLoopTomcat(int PORT) {
        this.PORT = PORT;
    }

    //1，初始化 servlet,解析xml配置文件
    //2，启动服务，打开监听，ServerSocket 端口8080
    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpResponseEncoder());
                            pipeline.addLast(new HttpRequestDecoder());
                            pipeline.addLast(new Handle());
                        }
                    });
        try {
            System.out.println("Tomcat 启动，监听端口："+PORT);
            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    class Handle extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("监听到"+msg);
            if(msg instanceof HttpRequest){
                HttpRequest httpRequest = (HttpRequest)msg;

                NettyRequest request = new NettyRequest(httpRequest);
                NettyResponse response = new NettyResponse(ctx);
                String url = request.getUrl();
                if (servletMap.containsKey(url)){
                    AbstractServlet servlet = servletMap.get(url);
                    servlet.service(request,response);
                }else {
                    response.writer("404");
                }
            }
        }
    }

    public static void main(String[] args) {
        new EventLoopTomcat(8080).start();
    }
}
