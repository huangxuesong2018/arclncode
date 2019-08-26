package com.gupao.edu.vip.nio.eventloop;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


import java.nio.charset.Charset;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class TestDecoder {
    public static void main(String[] args) {
        new TestDecoder().testLengthFieldBasedFrameDecoder();
    }
    public void testLengthFieldBasedFrameDecoder() {
        try {
            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>(){
                protected void initChannel(EmbeddedChannel ch) {
                    /*ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                            1024, // maxFrameLength - 发送的数据包最大长度；
                            0, //长度域偏移量，指的是长度域位于整个数据包字节数组中的下标；
                            4, //长度域的自己的字节数长度。
                            0, //长度域的偏移量矫正。 如果长度域的值，除了包含有效数据域的长度外，还包含了其他域
                                                 //（如长度域自身）长度，那么，就需要进行矫正。矫正的值为：包长 - 长度域的值 – 长度域偏移 – 长度域长。
                            4));//丢弃的起始字节数。丢弃处于有效数据前面的字节数量。比如前面有4个节点的长度域，则它的值为4。*/
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; j++) {
                ByteBuf buf = Unpooled.buffer();
                String s = "呵呵,Iam"+j;
                byte[] bytes = s.getBytes("UTF-8");
                //buf.writeInt(bytes.length);
                buf.writeBytes(bytes);
                channel.writeInbound(buf);
            }
        } catch (Exception e) {

        }
    }

    static class StringProcessHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("-"+msg+"-");
        }
    }
}
